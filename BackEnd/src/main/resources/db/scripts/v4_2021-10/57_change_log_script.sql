UPDATE authorities
SET name = 'canReadTopics'
WHERE id = 1;

UPDATE authorities
SET name = 'canAddToFavorites'
WHERE id = 2;

UPDATE authorities
SET name = 'canSubscribe'
WHERE id = 3;

INSERT INTO authorities (name)
VALUES ('canSendMessages');

INSERT INTO authorities (name)
VALUES ('canTakeSurveys');

INSERT INTO authorities (name)
VALUES ('canPostToSandbox');

INSERT INTO authorities (name)
VALUES ('canEditTopic');

INSERT INTO authorities (name)
VALUES ('canLeaveComments');

INSERT INTO authorities (name)
VALUES ('canEditComment');

INSERT INTO authorities (name)
VALUES ('canGetInvitation');

INSERT INTO authorities (name)
VALUES ('canGiveInvitaion');

INSERT INTO authorities (name)
VALUES ('canPostToHubs');

INSERT INTO authorities (name)
VALUES ('canDecreaseRights');

INSERT INTO authorities (name)
VALUES ('canModerateTopics');

INSERT INTO authorities (name)
VALUES ('canBan');

INSERT INTO authorities (name)
VALUES ('canDeleteComment');

INSERT INTO authorities (name)
VALUES ('canCreateCompany');

INSERT INTO authorities (name)
VALUES ('canEditCompany');

INSERT INTO authorities (name)
VALUES ('canDeleteCompany');