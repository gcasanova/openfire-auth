package com.i7.openfire.auth;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.jivesoftware.openfire.SharedGroupException;
import org.jivesoftware.openfire.roster.Roster;
import org.jivesoftware.openfire.roster.RosterItem;
import org.jivesoftware.openfire.user.User;
import org.jivesoftware.openfire.user.UserAlreadyExistsException;
import org.jivesoftware.openfire.user.UserNotFoundException;
import org.jivesoftware.openfire.user.UserProvider;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.JID;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.i7.openfire.config.DataConfig;
import com.i7.openfire.config.Properties;

import redis.clients.jedis.JedisCluster;

public class AppUserProvider implements UserProvider {
    private static final Logger log = LoggerFactory.getLogger(AppUserProvider.class);
    
    private static final String USER_PREFIX = "user#";
    private static final String USER_HASH_FIELD = "main";
    private static final String CONTACTS_PREFIX = "contacts#";

	@Override
	@SuppressWarnings("unchecked")
    public User loadUser(String accountId) throws UserNotFoundException {
		Properties properties = Properties.getInstance();
		JedisCluster jedis = DataConfig.getInstance().getJedis();
		
        log.debug("loadUser {}", accountId);
        if ("admin".equals(accountId))
            throw new UserNotFoundException("admin not managed with app server!");
        
        User user = null;
        String sUser = jedis.hget(USER_PREFIX + accountId, USER_HASH_FIELD);
        if (Strings.isNullOrEmpty(sUser))
        	throw new UserNotFoundException("user " + accountId + " not found!");
        
        try {
        	JSONObject jUser = (JSONObject) new JSONParser().parse(sUser);
            Set<String> sContacts = jedis.smembers(CONTACTS_PREFIX + accountId);
            
            String name = (String) jUser.get("name");
            String email = (String) jUser.getOrDefault("email", accountId + "@planout.com");
            Date creationDate = Date.from(Instant.ofEpochMilli((long) jUser.get("createdAt")));
            Date modificationDate = Date.from(Instant.ofEpochMilli((long) jUser.get("updatedAt")));
            
            user = new User(accountId, name, email, creationDate, modificationDate);
            
            Roster roster = user.getRoster();
            if (!sContacts.isEmpty()) {
                List<String> groups = Lists.newArrayList();
                groups.add("friends");
                
                Set<String> jids = Sets.newHashSet();
                for (RosterItem item : roster.getRosterItems()) {
                    jids.add(item.getJid().toBareJID().split("@")[0]);
                }
                
                if (!sContacts.equals(jids)) {
                    log.debug("User {} friends list is not the same than user's chat roster, making changes...", accountId);

                    // remove old friends from roster
                    Set<String> jidsCopy = Sets.newHashSet(jids);
                    jidsCopy.removeAll(sContacts);
                    for (String jid : jidsCopy) {
                        log.debug("Removing friend {}", jid);

                        roster.deleteRosterItem(new JID(jid, properties.getDomain(), ""), false);
                    }

                    // add new friends to roster
                    sContacts.removeAll(jids);
                    for (String friendId : sContacts) {
                        log.debug("Adding friend {}", friendId);

                        RosterItem item = roster.createRosterItem(new JID(friendId, properties.getDomain(), ""), name, groups, true, true);
                        item.setSubStatus(RosterItem.SUB_BOTH);
                        roster.updateRosterItem(item);
                    }
                }
            } else {
                // clear roster if user has no friends
                for (RosterItem item : roster.getRosterItems()) {
                    log.debug("Removing friend {}", item.getJid());

                    roster.deleteRosterItem(item.getJid(), false);
                }
            }
        } catch (SharedGroupException | ParseException | UserAlreadyExistsException e) {
            log.debug("Error while handling user", e.getMessage());
        }
        return user;
    }

    @Override
    public User createUser(String s, String s1, String s2, String s3) throws UserAlreadyExistsException {
        log.debug("createUser {}", s);
        return new User("void", "void", "void@void", new Date(), new Date());
    }

    @Override
    public void deleteUser(String s) {
        log.debug("deleteUser {}", s);
    }

    @Override
    public int getUserCount() {
        return 1;
    }

    @Override
    public Collection<User> getUsers() {
        log.debug("getUsers");
        final ArrayList<User> list = Lists.newArrayList();
        return list;
    }

    @Override
    public Collection<String> getUsernames() {
        log.debug("getUsernames");
        final ArrayList<String> list = Lists.newArrayList();
        return list;
    }

    @Override
    public Collection<User> getUsers(int i, int i1) {
        log.debug("loadUsers");
        final ArrayList<User> list = Lists.newArrayList();
        return list;
    }

    @Override
    public void setName(String s, String s1) throws UserNotFoundException {
        throw new UnsupportedOperationException("can't change user name");
    }

    @Override
    public void setEmail(String s, String s1) throws UserNotFoundException {
        throw new UnsupportedOperationException("can't change user email");
    }

    @Override
    public void setCreationDate(String s, Date date) throws UserNotFoundException {
        throw new UnsupportedOperationException("can't change creation date");
    }

    @Override
    public void setModificationDate(String s, Date date) throws UserNotFoundException {
        throw new UnsupportedOperationException("can't change modification date");
    }

    @Override
    public Set<String> getSearchFields() throws UnsupportedOperationException {
        return null;
    }

    @Override
    public Collection<User> findUsers(Set<String> strings, String s) throws UnsupportedOperationException {
        return null;
    }

    @Override
    public Collection<User> findUsers(Set<String> strings, String s, int i, int i1) throws UnsupportedOperationException {
        return null;
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }

    @Override
    public boolean isNameRequired() {
        return false;
    }

    @Override
    public boolean isEmailRequired() {
        return false;
    }
}
