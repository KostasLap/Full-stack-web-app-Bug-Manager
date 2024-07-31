CREATE TABLE `users` (
  `user_id` int PRIMARY KEY AUTO_INCREMENT,
  `user_name` varchar(255)
);

CREATE TABLE `bugs` (
  `bug_id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `description` text,
  `severity` int
);

CREATE TABLE `bug_report_info` (
  `report_id` int PRIMARY KEY AUTO_INCREMENT,
  `bug_id` int,
  `reporter_id` int,
  `date_added` datetime,
  `resolved` boolean,
  `date_resolved` datetime
);

CREATE TABLE `dependencies` (
  `dependency_id` int PRIMARY KEY AUTO_INCREMENT,
  `bug_id` int,
  `depend_on_bug_id` int
);

ALTER TABLE `bug_report_info` ADD FOREIGN KEY (`bug_id`) REFERENCES `bugs` (`bug_id`);

ALTER TABLE `bug_report_info` ADD FOREIGN KEY (`reporter_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `dependencies` ADD FOREIGN KEY (`bug_id`) REFERENCES `bugs` (`bug_id`);

ALTER TABLE `dependencies` ADD FOREIGN KEY (`depend_on_bug_id`) REFERENCES `bugs` (`bug_id`);
