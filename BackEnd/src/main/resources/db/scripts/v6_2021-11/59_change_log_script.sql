COMMENT ON table addresses is 'Адрес';
COMMENT ON column addresses.id is 'Первичный ключ';
COMMENT ON column addresses.country is 'Страна';
COMMENT ON column addresses.region is 'Регион';
COMMENT ON column addresses.city is 'Город';

COMMENT ON table authorities is 'Разрешения для ролей';
COMMENT ON column authorities.id is 'Первичный ключ';
COMMENT ON column authorities.name is 'Названия разрешений';

COMMENT ON table badge_users is 'Ачивки-пользователи';

COMMENT ON table badges is 'Ачивки пользователей';
COMMENT ON column badges.id is 'Первичный ключ';
COMMENT ON column badges.title is 'Имя аватарки';
COMMENT ON column badges.icon is 'Сама ачивка';

COMMENT ON table comments is 'Комментарий пользователя';
COMMENT ON column comments.id is 'Первичный ключ';
COMMENT ON column comments.content is 'Содержание публикации';
COMMENT ON column comments.published is 'Дата публикации';
COMMENT ON column comments.is_moderate is 'Изменен ли комментарий';
COMMENT ON column comments.topic_id is 'К топику с каким Id относится коммент';
COMMENT ON column comments.user_id is 'Id пользователя оставившего комментарий';
COMMENT ON column comments.main_comment_id is 'Id комментария верхнего порядка';
COMMENT ON column comments.is_removed is 'Комментарий удален';

COMMENT ON table comments_negative_votes is 'Комментарий-пользователи которые дизлайкнули';

COMMENT ON table comments_positive_votes is 'Комментарий-пользователи которые лайкнули';

COMMENT ON table companies is 'Компании';
COMMENT ON column companies.id is 'Первичный ключ';
COMMENT ON column companies.company_name is 'Первичный ключ';
COMMENT ON column companies.utr_number is 'Номер ИНН компании, необходимый для регистрации на сервисе';
COMMENT ON column companies.company_site is 'Сайт компании';
COMMENT ON column companies.company_scale is 'Количество человек, работающих в компании';
COMMENT ON column companies.company_avatar is 'Аватар компании';
COMMENT ON column companies.about_company is 'Биография компании, "о компании"';
COMMENT ON column companies.company_rating_id is 'Ссылка на Id рейтинга компании';
COMMENT ON column companies.is_enabled is 'Действующий аккаунт';

COMMENT ON table company_admins is 'Компании-пользователи с правами админа компании';

COMMENT ON table company_blog is 'Компании-блог компании, её публикации на ресурсе';

COMMENT ON table company_company_contacts is 'Компании-ссылки на сторонние веб-ресурсы компании';

COMMENT ON table company_followers is 'Компании-подписчики компании';

COMMENT ON table company_hubs is 'Компании-список Хабов, в которые совершила вклад компания';

COMMENT ON table company_moderators is 'Компании-пользователи с правами модератора компании';

COMMENT ON table company_tags is 'Компании-список компетенций/отраслей компании';

COMMENT ON table hubs is 'Хабы';
COMMENT ON column hubs.id is 'Первичный ключ';
COMMENT ON column hubs.title is 'Название хаба';
COMMENT ON column hubs.icon is 'Аватар хаба';

COMMENT ON table hubs_tags is 'Хабы-тэги хаба';

COMMENT ON table hubs_topics is 'Хабы-список топиков';

COMMENT ON table karma is 'Карма';
COMMENT ON column karma.id is 'Первичный ключ';
COMMENT ON column karma.value is 'Текущее значение кармы';
COMMENT ON column karma.reason is 'Текстовое описание причины изменения кармы';
COMMENT ON column karma.edited_user_id is 'Id пользователя, КОТОРОМУ поменяли карму';
COMMENT ON column karma.editor_user_id is 'Id пользователя, КОТОРЫЙ поменял карму другому';

COMMENT ON table profile_contact_info is 'Профили-ссылки на сторонние веб-ресурсы пользователя';

COMMENT ON table profile_specialization is 'Профили-компетенции пользователя';

COMMENT ON table profiles is 'Профили';
COMMENT ON column profiles.id is 'Первичный ключ';
COMMENT ON column profiles.avatar is 'Аватар пользователя';
COMMENT ON column profiles.gender is 'Пол пользователя';
COMMENT ON column profiles.actual_name is 'Настоящее имя пользователя';
COMMENT ON column profiles.birth_day is 'Дата рождения пользователя';
COMMENT ON column profiles.about_user is 'Биография пользователя, "о себе"';
COMMENT ON column profiles.registered_date is 'Дата регистрации';
COMMENT ON column profiles.address_id is 'Id адреса пользователя';
COMMENT ON column profiles.user_company_id is 'Id места работы пользователя';
COMMENT ON column profiles.user_id is 'Id пользователя';

COMMENT ON table rating is 'Рейтинг';
COMMENT ON column rating.id is 'Первичный ключ';
COMMENT ON column rating.value is 'Текущее значение рейтинга';
COMMENT ON column rating.reason is 'Текстовое описание причины изменения рейтинга';
COMMENT ON column rating.user_activity_id is 'Принадлежность изменения рейтинга к Id конкретной активности';

COMMENT ON table roles is 'Роли';
COMMENT ON column roles.id is 'Первичный ключ';
COMMENT ON column roles.roles_names is 'Имя роли';

COMMENT ON table roles_authorities is 'Роли-авторити';

COMMENT ON table speakers_companies is 'Компании-представители компании';

COMMENT ON table tags is 'Вкладки-теги на главной странице сайта';
COMMENT ON column tags.id is 'Первичный ключ';
COMMENT ON column tags.title is 'Название вкладки';

COMMENT ON table tags_topics is 'Теги-список постов по этому тэгу';

COMMENT ON table topics is 'Посты написанные пользователями';
COMMENT ON column topics.id is 'Первичный ключ';
COMMENT ON column topics.title is 'Название поста/заголовка';
COMMENT ON column topics.published is 'Дата публикации';
COMMENT ON column topics.content is 'Хранит информацию о содержании публикации';

COMMENT ON table topics_authors is 'Посты-авторы';

COMMENT ON table topics_views is 'Посты-пользователи просмотревшие посты';

COMMENT ON table topics_votes is 'Посты-пользователи проголосовавшие за посты';

COMMENT ON table user_activities is 'Активности пользователей';
COMMENT ON column user_activities.id is 'Первичный ключ';
COMMENT ON column user_activities.activity_date_time is 'Дата и время действия';
COMMENT ON column user_activities.is_change_up is 'Это повышение (true) или понижение (false) рейтинга пользователя';
COMMENT ON column user_activities.user_id is 'Id пользователей чья активность';

COMMENT ON table user_roles is 'Пользователи-роли';

COMMENT ON table users is 'Пользователи';
COMMENT ON column users.id is 'Первичный ключ';
COMMENT ON column users.username is 'Уникальный логин пользователя';
COMMENT ON column users.email is 'Уникальный почтовый адрес пользователя';
COMMENT ON column users.password is 'Пароль от аккаунта';
COMMENT ON column users.is_enabled is 'Активен/заблокирован';

COMMENT ON table users_bookmarks is 'Пользователи-закладки на публикацию';

COMMENT ON table users_contributions is 'Пользователи-вклад в хабы';

COMMENT ON table users_followers is 'Пользователи-подписчики пользователей';

COMMENT ON table users_followings is 'Пользователи-подписки пользователей на пользователей';

COMMENT ON table users_hubs is 'Пользователи-подписки пользователей на хабы';

COMMENT ON table users_invited is 'Пользователи-приглашенные пользователи';



