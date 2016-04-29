USE 'openfire';

INSERT INTO ofProperty (name, propValue) VALUES ('hybridAuthProvider.secondaryProvider.overrideList', 'admin') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('provider.auth.className', 'org.jivesoftware.openfire.auth.HybridAuthProvider') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('provider.user.className', 'org.jivesoftware.openfire.user.HybridUserProvider') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('hybridAuthProvider.primaryProvider.className', 'com.i7.openfire.auth.AppAuthProvider') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('hybridUserProvider.primaryProvider.className', 'com.i7.openfire.auth.AppUserProvider') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('hybridAuthProvider.secondaryProvider.className', 'org.jivesoftware.openfire.auth.DefaultAuthProvider') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('hybridUserProvider.secondaryProvider.className', 'org.jivesoftware.openfire.user.DefaultUserProvider') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);

INSERT INTO ofProperty (name, propValue) VALUES ('i7.redis.timeout', 5) ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('i7.redis.max.redirects', 6) ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('i7.issuer.name', 'planout') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('i7.key.path', '/usr/share/openfire/resources/security/public-key.der') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('i7.redis.nodes', '127.0.0.1:7000,127.0.0.1:7001,127.0.0.1:7002,127.0.0.1:7003,127.0.0.1:7004,127.0.0.1:7005') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);

INSERT INTO ofProperty (name, propValue) VALUES ('locale', 'en') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('adminConsole.port', 9090) ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('log.debug.enabled', 'true') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('adminConsole.securePort', 9091) ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);

INSERT INTO ofProperty (name, propValue) VALUES ('xmpp.domain', 'localhost') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('xmpp.offline.quota', 102400) ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('xmpp.offline.type', 'store') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('xmpp.proxy.enabled', 'false') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('xmpp.auth.anonymous', 'true') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('xmpp.privateStorageEnabled', 'false') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);

INSERT INTO ofProperty (name, propValue) VALUES ('database.defaultProvider.minConnections', 5) ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('database.defaultProvider.maxConnections', 25) ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('database.defaultProvider.testSQL', 'select 1') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('database.defaultProvider.testAfterUse', 'true') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('database.defaultProvider.testBeforeUse', 'true') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('database.defaultProvider.username', 'xxxx') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('database.defaultProvider.password', 'xxxx') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('database.defaultProvider.connectionTimeout', '1.0') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('database.defaultProvider.driver', 'com.mysql.jdbc.Driver') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('connectionProvider.className', 'org.jivesoftware.database.DefaultConnectionProvider') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('database.defaultProvider.serverURL', 'jdbc:mysql://localhost:3306/openfire?rewriteBatchedStatements=true') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);

INSERT INTO ofProperty (name, propValue) VALUES ('conversation.maxAge',  100) ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('conversation.maxTime', 300) ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('conversation.idleTime', 10) ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('conversation.roomsArchived', '') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('conversation.maxRetrievable', 100) ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('conversation.roomArchiving', 'false') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('conversation.messageArchiving', 'true') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
INSERT INTO ofProperty (name, propValue) VALUES ('conversation.metadataArchiving', 'true') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);

INSERT INTO ofProperty (name, propValue) VALUES ('setup', 'true') ON DUPLICATE KEY UPDATE propValue=VALUES(propValue);
