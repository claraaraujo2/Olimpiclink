CREATE TABLE `categories` (
  `id_category` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name_category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_category`)
);

CREATE TABLE `users` (
  `id_user` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name_user` varchar(255) DEFAULT NULL,
  `login_user` varchar(255) DEFAULT NULL,
  `password_user` varchar(255) DEFAULT NULL,
  `email_user` varchar(255) DEFAULT NULL,
  `profile_picture_user` blob DEFAULT NULL,
  `created_at_user` timestamp NULL DEFAULT current_timestamp(),
  `updated_at_user` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id_user`)
);
CREATE TABLE `cities` (
  `id_city` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name_city` varchar(255) DEFAULT NULL,
  `uf_city` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id_city`)
);

CREATE TABLE `comunities` (
  `id_comunity` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name_comunity` varchar(255) DEFAULT NULL,
  `description_comunity` text DEFAULT NULL,
  `icon_comunity` blob DEFAULT NULL,
  `created_at_comunities` timestamp NULL DEFAULT current_timestamp(),
  `updated_at_comunities` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id_comunity`)
);

CREATE TABLE `leaders` (
  `id_leader` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_leader`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `leaders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`)
);

CREATE TABLE `places` (
  `id_place` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `city_id` int(10) unsigned NOT NULL,
  `name_place` varchar(255) DEFAULT NULL,
  `description_place` text DEFAULT NULL,
  `opening_time_place` time DEFAULT NULL,
  `closing_time_place` time DEFAULT NULL,
  `created_at_place` timestamp NULL DEFAULT current_timestamp(),
  `updated_at_place` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id_place`),
  KEY `city_id` (`city_id`),
  CONSTRAINT `places_ibfk_1` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id_city`)
);

CREATE TABLE `events` (
  `idEvent` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `place_id` int(10) unsigned NOT NULL,
  `comunity_id` int(10) unsigned NOT NULL,
  `nameEvent` varchar(255) DEFAULT NULL,
  `descriptionEvent` text DEFAULT NULL,
  `dateTimeEvent` datetime DEFAULT NULL,
  `iconEvent` blob DEFAULT NULL,
  `closingDateTimeEvent` datetime DEFAULT NULL,
  `created_at_event` timestamp NULL DEFAULT current_timestamp(),
  `updated_at_event` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`idEvent`),
  KEY `comunity_id` (`comunity_id`),
  KEY `place_id` (`place_id`),
  CONSTRAINT `events_ibfk_1` FOREIGN KEY (`comunity_id`) REFERENCES `comunities` (`id_comunity`),
  CONSTRAINT `events_ibfk_2` FOREIGN KEY (`place_id`) REFERENCES `places` (`id_place`)
);

CREATE TABLE `event_user` (
  `Event_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Event_id`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `event_user_ibfk_1` FOREIGN KEY (`Event_id`) REFERENCES `events` (`idEvent`),
  CONSTRAINT `event_user_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`)
);

CREATE TABLE `category_event` (
  `category_id` int(10) unsigned NOT NULL,
  `Event_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`category_id`,`Event_id`),
  KEY `Event_id` (`Event_id`),
  CONSTRAINT `category_event_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id_category`),
  CONSTRAINT `category_event_ibfk_2` FOREIGN KEY (`Event_id`) REFERENCES `events` (`idEvent`)
);

CREATE TABLE `places_has_category` (
  `place_id` int(10) unsigned NOT NULL,
  `category_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`place_id`,`category_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `places_has_category_ibfk_1` FOREIGN KEY (`place_id`) REFERENCES `places` (`id_place`),
  CONSTRAINT `places_has_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id_category`)
);

CREATE TABLE `user_category` (
  `user_id` int(10) unsigned NOT NULL,
  `category_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`category_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `user_category_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`),
  CONSTRAINT `user_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id_category`)
);

CREATE TABLE `user_city` (
  `user_id` int(10) unsigned NOT NULL,
  `city_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`city_id`),
  KEY `city_id` (`city_id`),
  CONSTRAINT `user_city_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`),
  CONSTRAINT `user_city_ibfk_2` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id_city`)
);

CREATE TABLE `user_comunity` (
  `user_id` int(10) unsigned NOT NULL,
  `comunity_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`comunity_id`),
  KEY `comunity_id` (`comunity_id`),
  CONSTRAINT `user_comunity_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`),
  CONSTRAINT `user_comunity_ibfk_2` FOREIGN KEY (`comunity_id`) REFERENCES `comunities` (`id_comunity`)
);

CREATE TABLE `user_followers` (
  `user_id` int(10) unsigned NOT NULL,
  `follower_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`follower_id`),
  KEY `follower_id` (`follower_id`),
  CONSTRAINT `user_followers_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`),
  CONSTRAINT `user_followers_ibfk_2` FOREIGN KEY (`follower_id`) REFERENCES `users` (`id_user`)
);

CREATE TABLE `user_follows` (
  `user_id` int(10) unsigned NOT NULL,
  `follow_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`follow_id`),
  KEY `follow_id` (`follow_id`),
  CONSTRAINT `user_follows_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`),
  CONSTRAINT `user_follows_ibfk_2` FOREIGN KEY (`follow_id`) REFERENCES `users` (`id_user`)
);

CREATE TABLE `user_places` (
  `user_id` int(10) unsigned NOT NULL,
  `place_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`place_id`),
  KEY `place_id` (`place_id`),
  CONSTRAINT `user_places_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`),
  CONSTRAINT `user_places_ibfk_2` FOREIGN KEY (`place_id`) REFERENCES `places` (`id_place`)
);

CREATE TABLE `comunity_has_category` (
  `comunity_id` int(10) unsigned NOT NULL,
  `category_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`comunity_id`,`category_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `comunity_has_category_ibfk_1` FOREIGN KEY (`comunity_id`) REFERENCES `comunities` (`id_comunity`),
  CONSTRAINT `comunity_has_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id_category`)
);

CREATE TABLE `comunity_has_leader` (
  `comunity_id` int(10) unsigned NOT NULL,
  `leader_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`comunity_id`,`leader_id`),
  KEY `leader_id` (`leader_id`),
  CONSTRAINT `comunity_has_leader_ibfk_1` FOREIGN KEY (`comunity_id`) REFERENCES `comunities` (`id_comunity`),
  CONSTRAINT `comunity_has_leader_ibfk_2` FOREIGN KEY (`leader_id`) REFERENCES `leaders` (`id_leader`)
);

create table `publications`(
	id_publication integer primary key auto_increment,
    text_publication text not null,
    url_image_one_publication varchar(60),
    url_image_two_publication varchar(60),
    url_image_three_publication varchar(60),
    url_image_four_publication varchar(60),
    image_one_publication mediumblob,
    image_two_publication mediumblob,
    image_three_publication mediumblob,
    image_four_publication mediumblob,
    
    `comunity_id` int UNSIGNED,
    `place_id` int UNSIGNED,
    `event_id` int UNSIGNED,
	
    foreign key (`comunity_id`) references `comunities`(`id_comunity`),
    foreign key (`place_id`) references `places`(`id_place`),
    foreign key (`event_id`) references `events`(`idEvent`)
);
update publications set url_image_one_publication = "https://cdn-icons-png.flaticon.com/512/7022/7022927.png";
alter table publications add column url_image_four_publication varchar(60);

create table `categories_has_publications`(
  `publication_id` int(10) NOT NULL,
  `category_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`publication_id`,`category_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `categories_has_publications_ibfk_1` FOREIGN KEY (`publication_id`) REFERENCES `publications` (`id_publication`),
  CONSTRAINT `categories_has_publications_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id_category`)
);

INSERT INTO `users` VALUES 
(1,'Khaleu Sanches Mancini','uelahk','123','khaleusanches@gmail.com',NULL,'2024-07-28 11:26:13','2024-07-28 08:26:13'),
(2,'Felipy Souza','Felipy','123','felipysouza@gmail.com',NULL,'2024-07-28 11:27:07','2024-07-28 08:27:07'),
(3,'Gabriel','Gabriel','123','felipysouza@gmail.com',NULL,'2024-07-28 12:23:09','2024-07-28 09:23:09'),
(4,'Raony','Gabriel','123','felipysouza@gmail.com',NULL,'2024-07-28 12:23:09','2024-07-28 09:23:09'),
(5,'Clara','Gabriel','123','felipysouza@gmail.com',NULL,'2024-07-28 12:23:09','2024-07-28 09:23:09');

INSERT INTO `categories` VALUES (1,'Futebol');
INSERT INTO `cities` VALUES (1,'Diadema','SP');
INSERT INTO `comunities` VALUES (1,'Flamengo','Uma comunidade para flamenguistas',NULL,'2024-07-28 11:13:12','2024-07-28 08:13:12');
INSERT INTO `places` VALUES (1,1,'Praca Ayrton Senna','Uma praca com quadra no bairro Serraria','00:00:00','23:59:59','2024-07-28 11:23:34','2024-07-28 08:23:34');
INSERT INTO `leaders` VALUES (1,2);
INSERT INTO `events` VALUES (1,1,1,'Libertadores','Jogo','2024-07-30 12:00:00',NULL,NULL,'2024-07-28 11:44:24','2024-07-28 08:44:24');
INSERT INTO `comunity_has_category` VALUES (1,1);
INSERT INTO `comunity_has_leader` VALUES (1,1);
INSERT INTO `places_has_category` VALUES (1,1);
INSERT INTO `category_event` VALUES (1,1);
INSERT INTO `event_user` VALUES (1,1);
INSERT INTO `user_category` VALUES (1,1);
INSERT INTO `user_city` VALUES (1,1);
INSERT INTO `user_comunity` VALUES (1,1);
INSERT INTO `user_followers` VALUES (2,1),(1,2),(2,3),(2,4),(2,5);
INSERT INTO `user_follows` VALUES (1,2);
INSERT INTO `user_places` VALUES (1,1);

select users.name_user as "Nome do usuario", name_city, name_category, seguidores.name_user as "Seguidores", seguindo.name_user as "Seguindo", name_comunity, name_place  from users 
left join user_city on users.id_user = user_city.user_id 
left join cities on user_city.city_id = cities.id_city 

left join user_category on users.id_user = user_category.user_id 
left join categories on user_category.category_id = categories.id_category 

left join user_followers on users.id_user = user_followers.user_id 
left join users seguidores on user_followers.follower_id = seguidores.id_user 

left join user_follows on users.id_user = user_follows.user_id 
left join users seguindo on user_follows.follow_id = seguindo.id_user 

left join user_comunity on users.id_user = user_comunity.user_id 
left join comunities on user_comunity.comunity_id = comunities.id_comunity 

left join user_places on users.id_user = user_places.user_id 
left join places on user_places.place_id = places.id_place;
