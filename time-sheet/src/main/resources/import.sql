INSERT INTO public.category_entity (is_deleted, name) VALUES (false, 'Design');
INSERT INTO public.category_entity (is_deleted, name) VALUES (false, 'Front-End Development');

INSERT INTO public.country_entity (is_deleted, name) VALUES (false, 'Srbija');
INSERT INTO public.country_entity (is_deleted, name) VALUES (false, 'Italija');
INSERT INTO public.country_entity (is_deleted, name) VALUES (false, 'Spanija');

INSERT INTO public.team_member_entity (hours_per_week, is_deleted, role, status, email, name, password, username) VALUES (40, false, 0, 0, 'nikola@gmail.com', 'Nikola Nikolic', '$2a$10$gviA8hU/zQgelKwdWjCTEu7VNwcJ7VZWF9fLLmRjninubwnwZCa42', 'nikola10');
INSERT INTO public.team_member_entity (hours_per_week, is_deleted, role, status, email, name, password, username) VALUES (42, false, 1, 0, 'stefan@gmail.com', 'Stefan Stefanovic', '$2a$10$gviA8hU/zQgelKwdWjCTEu7VNwcJ7VZWF9fLLmRjninubwnwZCa42', 'stefan10');
INSERT INTO public.team_member_entity (hours_per_week, is_deleted, role, status, email, name, password, username) VALUES (35, false, 0, 0, 'marija@gmail.com', 'Marija Marjanovic', '$2a$10$gviA8hU/zQgelKwdWjCTEu7VNwcJ7VZWF9fLLmRjninubwnwZCa42', 'marija10');
INSERT INTO public.team_member_entity (hours_per_week, is_deleted, role, status, email, name, password, username) VALUES (40, false, 0, 0, 'nikolina@gmail.com', 'Nikolina Nikolic', '$2a$10$gviA8hU/zQgelKwdWjCTEu7VNwcJ7VZWF9fLLmRjninubwnwZCa42', 'nikolina10');


INSERT INTO public.client_entity (is_deleted, country_id, address, city, name, postal_code) VALUES (false, 1, 'Nikole Pasica 10', 'Novi Sad', 'Klijent 1', '21000');
INSERT INTO public.client_entity (is_deleted, country_id, address, city, name, postal_code) VALUES (false, 1, 'Zmaj Jovina 11', 'Beograd', 'Klijent 2', '11000');
INSERT INTO public.client_entity (is_deleted, country_id, address, city, name, postal_code) VALUES (false, 1, 'Tekelijina 20', 'Nis', 'Klijent 3', '18000');

INSERT INTO public.project_entity (is_deleted, status, client_id, lead_id, description, name) VALUES (false, 0, 1, 1, 'Opis projekta 1', 'Projekat 1');
INSERT INTO public.project_entity (is_deleted, status, client_id, lead_id, description, name) VALUES (false, 0, 1, 3, 'Opis projekta 2', 'Projekat 2');
INSERT INTO public.project_entity (is_deleted, status, client_id, lead_id, description, name) VALUES (false, 0, 2, 4, 'Opis projekta 3', 'Projekat 3');
INSERT INTO public.project_entity (is_deleted, status, client_id, lead_id, description, name) VALUES (false, 0, 3, 1, 'Opis projekta 4', 'Projekat 4');
INSERT INTO public.project_entity (is_deleted, status, client_id, lead_id, description, name) VALUES (false, 0, 3, 3, 'Opis projekta 5', 'Projekat 5');
INSERT INTO public.project_entity (is_deleted, status, client_id, lead_id, description, name) VALUES (false, 1, 2, 1, 'Opis projekta 6', 'Projekat 6');
INSERT INTO public.project_entity (is_deleted, status, client_id, lead_id, description, name) VALUES (false, 0, 1, 4, 'Opis projekta 7', 'Projekat 7');
INSERT INTO public.project_entity (is_deleted, status, client_id, lead_id, description, name) VALUES (false, 0, 2, 3, 'Opis projekta 8', 'Projekat 8');

INSERT INTO public.time_sheet_item_entity (date, overtime, "time", category_id, project_id, team_member_id, description) VALUES ('2023-10-10', 1.5, 7.5, 1, 1, 1, 'Opis 1');
INSERT INTO public.time_sheet_item_entity (date, overtime, "time", category_id, project_id, team_member_id, description) VALUES ('2023-10-10', 0,  7.5, 1, 1, 3, 'Opis 2');
INSERT INTO public.time_sheet_item_entity (date, overtime, "time", category_id, project_id, team_member_id, description) VALUES ('2023-10-10', 1.5, 4.5, 2, 1, 4, 'Opis 3');
INSERT INTO public.time_sheet_item_entity (date, overtime, "time", category_id, project_id, team_member_id, description) VALUES ('2023-10-11', 1.5, 6.5, 2, 1, 1, 'Opis 4');
INSERT INTO public.time_sheet_item_entity (date, overtime, "time", category_id, project_id, team_member_id, description) VALUES ('2023-10-11', 2.5, 7.5, 1, 2, 3, 'Opis 5');
INSERT INTO public.time_sheet_item_entity (date, overtime, "time", category_id, project_id, team_member_id, description) VALUES ('2023-10-11', 0,  6.5, 2, 3, 4, 'Opis 6');
INSERT INTO public.time_sheet_item_entity (date, overtime, "time", category_id, project_id, team_member_id, description) VALUES ('2023-10-12', 0,  6.5, 1, 1, 1, 'Opis 7');
INSERT INTO public.time_sheet_item_entity (date, overtime, "time", category_id, project_id, team_member_id, description) VALUES ('2023-10-12', 0,  3.5, 2, 2, 1, 'Opis 8');
INSERT INTO public.time_sheet_item_entity (date, overtime, "time", category_id, project_id, team_member_id, description) VALUES ('2023-10-12', 2.5, 2.5, 1, 3, 3, 'Opis 9');
INSERT INTO public.time_sheet_item_entity (date, overtime, "time", category_id, project_id, team_member_id, description) VALUES ('2023-10-12', 1, 7.5, 1, 3, 4, 'Opis 10');
INSERT INTO public.time_sheet_item_entity (date, overtime, "time", category_id, project_id, team_member_id, description) VALUES ('2023-10-13', 0, 4.5, 1, 2, 1, 'Opis 11');






