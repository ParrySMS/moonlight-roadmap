-- there is MYSQL keyword also call rank, so we use `rank`
INSERT INTO `roadmap`.`pl_goal` (id, jira_number_id, name, description, `rank`
	, state, health, report_color, start_date, target_complete_date
	, portfolio_ask_date, releases, release_start_date, release_end_date, story_points_total
	, story_points_accepted)
VALUES ('007febb5934d4a5bb462fae99cc1a334', 72, 'Central: Maximize Talent', NULL, 1000
		, 2, 5, '#ff4e4d', NULL, NULL
		, NULL, 'ITPPM 2019', '2019-10-22', '2019-12-31', 0
		, 0),
	('012b305d82f34d7899e58900db10087c', 23, 'PLACEHOLDER - DRIP  (Scott Burns)', NULL, 30
		, 2, 5, '#0d2c54', NULL, NULL
		, NULL, 'Data Services - August-September 2019, Data Services - November-December 2019, Data Services - October-November 2019', '2019-08-14', '2019-12-31', 156
		, 147),
	('0200b3f1ee7f4fb2a633f89b06afd600', 85, 'Software: Deliver consistent UX Elements', NULL, 7
		, 2, 5, '#6ec762', NULL, NULL
		, NULL, 'Cloud - Nov-Dec 2019, Cloud_Q1 2020, Cloud_Q2 2020, Cloud_Q3 2020, Cloud_Q4 2020', '2019-11-14', '2020-12-31', 77
		, 64),
	('0204ad4d40d94d50b29c07a3c8d1ac17', 34, 'MIM: Frictionless Technology Platform', 'We will modernize and extend our technology platform so that we have the capabilities and advisor experience that delights our clients.', 25
		, 2, 5, '#046895', NULL, NULL
		, NULL, 'Managed Portfolios - Q2 2019, Managed Portfolios - Q3 2019, Managed Portfolios 2019Q4', '2019-04-01', '2019-12-31', 1475
		, 1261),
	('05a1b17651a649bbb0d3f4e1ca092ad6', 57, 'Test Unattached Theme #1', 'Test Unattached Theme #1', 32
		, 1, 5, '#a59e89', NULL, NULL
		, NULL, '', NULL, NULL, 5
		, 0),
	('0734b06b90224c1eb0f0923a1c040f81', 82, 'Software: Expand goals-based investment planning globally', NULL, 9
		, 2, 5, '#9faebf', NULL, NULL
		, NULL, 'Financial Planning - Q1 2020, Financial Planning - Q2 2020, Financial Planning - Q3 2020, Financial Planning - Q4 2019, Financial Planning - Q4 2020', '2019-10-01', '2020-12-31', 32
		, 21),
	('0760c079a00e4ff998b51eb67a501303', 25, 'Workplace: Sign up four large plan sponsor clients in Retirement Manager through our recordkeeper relationships', NULL, 26
		, 2, 5, '#FF6D27', NULL, NULL
		, NULL, 'Retirement Manager 2019 Q3, Retirement Manager 2019 Q4', '2019-07-03', '2019-12-31', 0
		, 0),
	('08380c3fa3ca402ab9644660ad6f5bbb', 74, 'Global: Surface new Morningstar data and research', NULL, 19
		, 2, 1, '#6EBDAB', '2019-09-30', '2020-12-28'
		, NULL, 'Cloud - Nov-Dec 2019, Cloud - Oct - Nov 2019, Cloud_Q1 2020, Cloud_Q2 2020, Cloud_Q3 2020, Cloud_Q4 2020, Data Services - April-May 2020, Data Services - February-March 2020, Data Services - January-February 2020, Data Services - November-December 2019', '2019-10-03', '2020-12-31', 0
		, 0),
	('0a32a901c61c4f09bfca38c9f72bba99', 18, 'Software: Research, analysis and comparison of 3rd party strategies within Cloud', 'Allow our users to perform analysis on an investment strategy so that they are not bound by vehicle type', 11
		, 2, 5, '#FF6D27', '2019-05-29', '2020-12-30'
		, NULL, 'Cloud - Aug-Sept 2019, Cloud - Jul-Aug 2019, Cloud - Jun-Jul 2019, Cloud - Nov-Dec 2019, Cloud - Oct - Nov 2019, Cloud_Q1 2020, Cloud_Q2 2020, Cloud_Q3 2020, Cloud_Q4 2020, Data Services - April-May 2020, Data Services - February-March 2020, Data Services - November-December 2019, Data Services - Q1 2020, Data Services - Q2 2020, Data Services - Q3 2020', '2019-05-30', '2020-12-31', 536
		, 218),
	('0df79ddbb5874354968ca15add24a66a', 73, 'Central: Modernize and Migrate', NULL, 1000
		, 2, 5, '#3399FF', '2019-10-22', '2019-12-31'
		, NULL, 'ITPPM 2019', '2019-10-22', '2019-12-31', 214
		, 154),
	('174093b8bd2640b2b89f22da2b4e3482', 33, 'MIM: Operational Scalability', 'We will design and deploy smart internal processes, systems, and data analytics to deliver the highest quality output at sustainable operational costs.', 24
		, 2, 5, '#ff4e4d', NULL, NULL
		, NULL, 'Managed Portfolios - Q2 2019, Managed Portfolios - Q3 2019, Managed Portfolios 2019Q4', '2019-04-01', '2019-12-31', 622
		, 552),
	('17a92e6ba69145b3b979301bd1a6aa6c', 26, 'Workplace: Deliver three large plan features to Retirement Manager in 2019', '#NAME?', 28
		, 2, 5, '#046895', '2019-07-03', '2019-12-31'
		, NULL, 'Retirement Manager 2019 Q3, Retirement Manager 2019 Q4', '2019-07-03', '2019-12-31', 422
		, 394),
	('1a5b755003564edd9325d73c9c31b606', 96, 'Can Themes be Sized?', NULL, 1000
		, 1, 5, '#6ec762', NULL, NULL
		, NULL, '', NULL, NULL, 0
		, 0),
	('1ae1629ba12e495f8e8b4f79f5b689e4', 76, 'Software: Create a self-service learning experience so that our users can discover the value of our software quickly and efficiently', 'Measure of success = usability and desirability; reduce overhead on support teams', 8
		, 2, 1, '#ff9867', '2019-10-01', '2020-12-29'
		, NULL, 'Cloud - Nov-Dec 2019, Cloud - Oct - Nov 2019, Cloud_Q1 2020, Cloud_Q2 2020, Cloud_Q3 2020, Cloud_Q4 2020', '2019-10-03', '2020-12-31', 310
		, 145);
