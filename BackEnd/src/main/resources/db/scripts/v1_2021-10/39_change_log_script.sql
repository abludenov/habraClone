INSERT INTO badge_users (badge_id, users_id)
VALUES (1, 1);
INSERT INTO badge_users (badge_id, users_id)
VALUES (2, 2);
INSERT INTO badge_users (badge_id, users_id)
VALUES (3, 3);

INSERT INTO company_admins (company_id, user_id)
VALUES (1, 1);
INSERT INTO company_admins (company_id, user_id)
VALUES (2, 2);
INSERT INTO company_admins (company_id, user_id)
VALUES (3, 3);

INSERT INTO company_blog (company_id, topic_id)
VALUES (1, 1);
INSERT INTO company_blog (company_id, topic_id)
VALUES (2, 2);
INSERT INTO company_blog (company_id, topic_id)
VALUES (3, 3);

INSERT INTO company_company_contacts (company_id, company_contacts)
VALUES (1, 'www.ru');
INSERT INTO company_company_contacts (company_id, company_contacts)
VALUES (2, 'www1.ru');
INSERT INTO company_company_contacts (company_id, company_contacts)
VALUES (3, 'www2.ru');

INSERT INTO company_followers (company_id, follower_id)
VALUES (1, 3);
INSERT INTO company_followers (company_id, follower_id)
VALUES (2, 3);
INSERT INTO company_followers (company_id, follower_id)
VALUES (3, 1);

INSERT INTO company_hubs (company_id, hub_id)
VALUES (1, 1);
INSERT INTO company_hubs (company_id, hub_id)
VALUES (2, 2);
INSERT INTO company_hubs (company_id, hub_id)
VALUES (3, 3);

INSERT INTO company_moderators (company_id, user_id)
VALUES (1, 1);
INSERT INTO company_moderators (company_id, user_id)
VALUES (2, 2);
INSERT INTO company_moderators (company_id, user_id)
VALUES (3, 3);

INSERT INTO company_tags (company_id, tag_id)
VALUES (1, 1);
INSERT INTO company_tags (company_id, tag_id)
VALUES (2, 2);
INSERT INTO company_tags (company_id, tag_id)
VALUES (3, 3);

INSERT INTO hubs_tags (tag_id, hub_id)
VALUES (1, 1);
INSERT INTO hubs_tags (tag_id, hub_id)
VALUES (2, 2);
INSERT INTO hubs_tags (tag_id, hub_id)
VALUES (3, 3);

INSERT INTO hubs_topics (hub_id, topic_id)
VALUES (1, 1);
INSERT INTO hubs_topics (hub_id, topic_id)
VALUES (2, 3);
INSERT INTO hubs_topics (hub_id, topic_id)
VALUES (3, 2);

INSERT INTO profile_contact_info (profile_id, contact_info)
VALUES (1, 1);
INSERT INTO profile_contact_info (profile_id, contact_info)
VALUES (2, 2);
INSERT INTO profile_contact_info (profile_id, contact_info)
VALUES (3, 3);

INSERT INTO profile_specialization (profile_id, specialization)
VALUES (1, 'Database Administrator');
INSERT INTO profile_specialization (profile_id, specialization)
VALUES (2, 'Database Moderator');
INSERT INTO profile_specialization (profile_id, specialization)
VALUES (3, 'Observer');

INSERT INTO roles_authorities (authority_id, role_id)
VALUES (1, 1);
INSERT INTO roles_authorities (authority_id, role_id)
VALUES (2, 2);
INSERT INTO roles_authorities (authority_id, role_id)
VALUES (3, 3);

INSERT INTO speakers_companies (company_id, profile_id)
VALUES (1, 1);
INSERT INTO speakers_companies (company_id, profile_id)
VALUES (2, 2);
INSERT INTO speakers_companies (company_id, profile_id)
VALUES (3, 3);

INSERT INTO tags_topics (tag_id, topic_id)
VALUES (1, 1);
INSERT INTO tags_topics (tag_id, topic_id)
VALUES (2, 2);
INSERT INTO tags_topics (tag_id, topic_id)
VALUES (3, 3);

INSERT INTO topics_authors (authors_id, topic_id)
VALUES (1, 1);
INSERT INTO topics_authors (authors_id, topic_id)
VALUES (2, 2);
INSERT INTO topics_authors (authors_id, topic_id)
VALUES (3, 3);

INSERT INTO topics_views (topic_id, views_id)
VALUES (1, 1);
INSERT INTO topics_views (topic_id, views_id)
VALUES (2, 2);
INSERT INTO topics_views (topic_id, views_id)
VALUES (3, 3);

INSERT INTO topics_votes (topic_id, vote_id)
VALUES (1, 1);
INSERT INTO topics_votes (topic_id, vote_id)
VALUES (2, 2);
INSERT INTO topics_votes (topic_id, vote_id)
VALUES (3, 3);

INSERT INTO user_roles (role_id, user_id)
VALUES (1, 1);
INSERT INTO user_roles (role_id, user_id)
VALUES (2, 2);
INSERT INTO user_roles (role_id, user_id)
VALUES (3, 3);

INSERT INTO users_bookmarks (bookmark_id, user_id)
VALUES (2, 2);
INSERT INTO users_bookmarks (bookmark_id, user_id)
VALUES (3, 3);
INSERT INTO users_bookmarks (bookmark_id, user_id)
VALUES (1, 1);

INSERT INTO users_companies_follower (follower_company_id, profile_id)
VALUES (1, 1);
INSERT INTO users_companies_follower (follower_company_id, profile_id)
VALUES (2, 2);
INSERT INTO users_companies_follower (follower_company_id, profile_id)
VALUES (3, 3);

INSERT INTO users_contributions (contribution_id, user_id)
VALUES (1, 1);
INSERT INTO users_contributions (contribution_id, user_id)
VALUES (2, 2);
INSERT INTO users_contributions (contribution_id, user_id)
VALUES (3, 3);

INSERT INTO users_followers (follower_id, user_id)
VALUES (1, 2);
INSERT INTO users_followers (follower_id, user_id)
VALUES (2, 3);
INSERT INTO users_followers (follower_id, user_id)
VALUES (3, 1);

INSERT INTO users_followings (following_id, user_id)
VALUES (1, 2);
INSERT INTO users_followings (following_id, user_id)
VALUES (2, 3);
INSERT INTO users_followings (following_id, user_id)
VALUES (3, 1);

INSERT INTO users_hubs (hubs_id, user_id)
VALUES (1, 1);
INSERT INTO users_hubs (hubs_id, user_id)
VALUES (2, 2);
INSERT INTO users_hubs (hubs_id, user_id)
VALUES (3, 3);

INSERT INTO users_invited (invited_user_id, user_id)
VALUES (1, 2);
INSERT INTO users_invited (invited_user_id, user_id)
VALUES (2, 3);