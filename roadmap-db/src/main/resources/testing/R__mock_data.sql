-- delete original mock datas to keep unique

DELETE FROM `roadmap`.`epic` WHERE
    id LIKE '%Mock%' or id LIKE '%mock%';

DELETE FROM `roadmap`.`initiative` WHERE
    id LIKE '%Mock%' or id LIKE '%mock%';

DELETE FROM `roadmap`.`program` WHERE
    name LIKE '%Mock%' or name LIKE '%mock%';

DELETE FROM `roadmap`.`epic_program` WHERE
        epic_id LIKE '%Mock%' or epic_id LIKE '%mock%'
        or program_name LIKE '%Mock%' or program_name LIKE '%mock%';

DELETE FROM `roadmap`.`initiative_program` WHERE
        initiative_id LIKE '%Mock%' or initiative_id LIKE '%mock%'
        or program_name LIKE '%Mock%' or program_name LIKE '%mock%';

DELETE FROM `roadmap`.`pl_goal_program` WHERE
        pl_goal_id LIKE '%Mock%' or pl_goal_id LIKE '%mock%'
        or program_name LIKE '%Mock%' or program_name LIKE '%mock%';

--
-- `roadmap`.`initiative`
--
INSERT INTO `roadmap`.`initiative` (id, jira_number_id, name, description, `rank`
	, state, health, report_color, start_date, target_complete_date
	, portfolio_ask_date, releases, release_start_date, release_end_date, story_points_total
	, story_points_accepted, pl_goal_id, pl_goal_name)
VALUES ('initiativeMockUUID1', 62166, 'initiativeMockInit1', NULL, 100
		, 1, 5, '#ff4e4d', NULL, NULL
		, NULL, 'ITPPM 2019', '2019-10-22', '2019-11-30', 0
		, 0, '007febb5934d4a5bb462fae99cc1a334', 'Central: Maximize Talent'),
	('initiativeMockUUID2', 53165, 'initiativeMockInit2', NULL, 100
		, 1, 3, '#ff3e4d', NULL, NULL
		, NULL, 'SRWQR 2019', '2019-11-15', '2019-12-31', 0
		, 0, '007febb5934d4a5bb462fae99cc1a334', 'Central: Maximize Talent'),
	('initiativeMockUUID3', 57212, 'initiativeMockInit3', NULL, 100
		, 1, 4, '#ff4e4d', NULL, NULL
		, NULL, 'GKWEQ 2019', '2019-11-03', '2019-11-24', 0
		, 0, '007febb5934d4a5bb462fae99cc1a334', 'Central: Maximize Talent');

--
-- `roadmap`.`epic`
--
INSERT INTO `roadmap`.`epic` (id, jira_number_id, name, description, `rank`
	, state, health, report_color, start_date, target_complete_date
	, portfolio_ask_date, releases, release_start_date, release_end_date, story_points_total
	, story_points_accepted, initiative_id, initiative_name)
VALUES ('epicMockUUID1', 12512, 'epicMockInit1', NULL, 100
		, 1, 5, '#ff4e4d', NULL, NULL
		, NULL, 'EEEE 2019', '2019-10-22', '2019-11-30', 0
		, 0, 'initiativeMockUUID1', 'initiativeMockInit1'),
	('epicMockUUID2', 36566, 'epicMockInit2', NULL, 100
		, 1, 3, '#ff3e4d', NULL, NULL
		, NULL, 'WWSQ 2019', '2019-11-15', '2019-11-21', 0
		, 0, 'initiativeMockUUID1', 'initiativeMockInit1'),
	('epicMockUUID3', 16831, 'epicMockInit3', NULL, 100
		, 1, 4, '#ff4e4d', NULL, NULL
		, NULL, 'GKWEQ 2019', '2019-11-15', '2019-11-29', 0
		, 0, 'initiativeMockUUID2', 'initiativeMockInit2'),
	('epicMockUUID4', 47623, 'epicMockInit4', NULL, 100
		, 1, 5, '#ff4e4d', NULL, NULL
		, NULL, 'ITPPM 2019', '2019-11-25', '2019-12-23', 0
		, 0, 'initiativeMockUUID2', 'initiativeMockInit2'),
	('epicMockUUID5', 27124, 'epicMockInit5', NULL, 100
		, 1, 3, '#ff3e4d', NULL, NULL
		, NULL, 'SRWQR 2019', '2019-11-10', '2019-11-23', 0
		, 0, 'initiativeMockUUID3', 'initiativeMockInit3'),
	('epicMockUUID6', 84413, 'epicMockInit6', NULL, 100
		, 1, 4, '#ff4e4d', NULL, NULL
		, NULL, 'GKWEQ 2019', '2019-11-13', '2019-11-24', 0
		, 0, 'initiativeMockUUID3', 'initiativeMockInit3');

--
-- `roadmap`.`program`
--
INSERT INTO `roadmap`.`program` (`name`) VALUES ('MockProgramAAA');
INSERT INTO `roadmap`.`program` (`name`) VALUES ('MockProgramBBB');
INSERT INTO `roadmap`.`program` (`name`) VALUES ('MockProgramCCC');
INSERT INTO `roadmap`.`program` (`name`) VALUES ('MockProgramDDD');
INSERT INTO `roadmap`.`program` (`name`) VALUES ('MockProgramEEE');
INSERT INTO `roadmap`.`program` (`name`) VALUES ('MockProgramFFF');

--
-- `roadmap`.`pl_goal_program`
--
INSERT INTO `roadmap`.`pl_goal_program` (`pl_goal_id`, `program_name`)
VALUES ('007febb5934d4a5bb462fae99cc1a334', 'MockProgramAAA'),
    ('012b305d82f34d7899e58900db10087c','MockProgramBBB');

--
-- `roadmap`.`initiative_program`
--
INSERT INTO `roadmap`.`initiative_program` (`initiative_id`, `program_name`)
VALUES ('initiativeMockUUID1', 'MockProgramAAA'),
       ('initiativeMockUUID2','MockProgramAAA'),
       ('initiativeMockUUID3','MockProgramAAA');

--
-- `roadmap`.`epic_program`
--
INSERT INTO `roadmap`.`epic_program` (`epic_id`, `program_name`)
VALUES ('epicMockUUID1', 'MockProgramAAA'),
       ('epicMockUUID2', 'MockProgramAAA'),
       ('epicMockUUID3', 'MockProgramAAA'),
       ('epicMockUUID4', 'MockProgramAAA'),
       ('epicMockUUID5', 'MockProgramAAA'),
       ('epicMockUUID6', 'MockProgramAAA');
