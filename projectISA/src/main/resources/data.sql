insert into user (id, username, password, email, name, last_name, city, telephone_number, role, last_password_reset_date,enabled) values (21,'David', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','melegi@gmail.com','David','Melegi','Zrenjanin','06334321',2,'2017-08-19 12:17:55',true);
insert into user (id, username, password, email, name, last_name, city, telephone_number, role, last_password_reset_date,enabled) values (22,'Rodja', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','rodja@gmail.com','Dusan','Radj','Zrenjanin','060123123',1, '2017-08-19 12:17:55',true);
insert into user (id, username, password, email, name, last_name, city, telephone_number, role, last_password_reset_date,enabled) values (23,'Kumara', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','kum@gmail.com','Aleksandar','Joksimovic','Leskovac','060123123',0, '2017-08-19 12:17:55',true);
insert into user (id, username, password, email, name, last_name, city, telephone_number, role, last_password_reset_date,enabled) values (24,'Pera', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','pera@gmail.com','Pera','Petrovic','Beograd','060123123',0, '2017-08-19 12:17:55',true);
insert into user (id, username, password, email, name, last_name, city, telephone_number, role, last_password_reset_date,enabled) values (25,'Zika', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','zika@gmail.com','Zika','Zivkovic','Beograd','060123123',1, '2017-08-19 12:17:55',true);
insert into user (id, username, password, email, name, last_name, city, telephone_number, role, last_password_reset_date,enabled) values (26,'Laza', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra','laza@gmail.com','Laza','Lazarevic','Beograd','060123123',1, '2017-08-19 12:17:55',true);

insert into authority (id, name) values (1, 'REGISTERED_USER');
insert into authority (id, name) values (2, 'SERVICE_ADMIN');
insert into authority (id, name) values (3, 'AVIO_COMPANY_ADMIN');


insert into user_authority (user_id, authority_id) VALUES (21, 1);
insert into user_authority (user_id, authority_id) VALUES (22, 1);
insert into user_authority (user_id, authority_id) VALUES (22, 2);
insert into user_authority (user_id, authority_id) VALUES (23, 3);


insert into friendship (id, accepted, user1_id, user2_id) values (21, true, 21, 22);
insert into friendship (id, accepted, user1_id, user2_id) values (22, true, 21, 23);
insert into friendship (id, accepted, user1_id, user2_id) values (23, true, 21, 24);
insert into friendship (id, accepted, user1_id, user2_id) values (24, true, 21, 25);
insert into friendship (id, accepted, user1_id, user2_id) values (25, true, 21, 26);

insert into friendship (id, accepted, user1_id, user2_id) values (26, true, 22, 21);
insert into friendship (id, accepted, user1_id, user2_id) values (27, true, 22, 23);
insert into friendship (id, accepted, user1_id, user2_id) values (28, true, 22, 24);
insert into friendship (id, accepted, user1_id, user2_id) values (29, true, 22, 25);
insert into friendship (id, accepted, user1_id, user2_id) values (30, true, 22, 26);

insert into friendship (id, accepted, user1_id, user2_id) values (31, true, 23, 21);
insert into friendship (id, accepted, user1_id, user2_id) values (32, true, 23, 22);
insert into friendship (id, accepted, user1_id, user2_id) values (33, true, 23, 24);
insert into friendship (id, accepted, user1_id, user2_id) values (34, true, 23, 25);
insert into friendship (id, accepted, user1_id, user2_id) values (35, true, 23, 26);

insert into friendship (id, accepted, user1_id, user2_id) values (36, true, 24, 21);
insert into friendship (id, accepted, user1_id, user2_id) values (37, true, 24, 22);
insert into friendship (id, accepted, user1_id, user2_id) values (38, true, 24, 23);
insert into friendship (id, accepted, user1_id, user2_id) values (39, true, 24, 25);
insert into friendship (id, accepted, user1_id, user2_id) values (40, true, 24, 26);

insert into friendship (id, accepted, user1_id, user2_id) values (41, true, 25, 21);
insert into friendship (id, accepted, user1_id, user2_id) values (42, true, 25, 22);
insert into friendship (id, accepted, user1_id, user2_id) values (43, true, 25, 23);
insert into friendship (id, accepted, user1_id, user2_id) values (44, true, 25, 24);
insert into friendship (id, accepted, user1_id, user2_id) values (45, true, 25, 26);

insert into friendship (id, accepted, user1_id, user2_id) values (46, true, 26, 21);
insert into friendship (id, accepted, user1_id, user2_id) values (47, true, 26, 22);
insert into friendship (id, accepted, user1_id, user2_id) values (48, true, 26, 23);
insert into friendship (id, accepted, user1_id, user2_id) values (49, true, 26, 24);
insert into friendship (id, accepted, user1_id, user2_id) values (50, true, 26, 25);

insert into avio_company (id,name,address_id,description,average_rating, is_deleted) values (121,'AirSerbia',101,'etihad uzo', 2.0, false);
insert into avio_company (id,name,address_id,description,average_rating, is_deleted) values (122,'JAt',102,'etihad nije uzo', 5.0, false);

insert into avio_flight (id,date_time_start, date_time_finish,flight_duration,flight_distance,price, start_location_id, end_location_id, avio_company_id, is_deleted, economy_class_seats, business_class_seats, first_class_seats) values (112,"2019-02-03 02:48:03", "2019-02-03 02:48:03", 130, 3500, 350, 102, 103, 121, false, 20, 20, 20);
insert into avio_flight (id,date_time_start, date_time_finish,flight_duration,flight_distance,price, start_location_id, end_location_id, avio_company_id, is_deleted, economy_class_seats, business_class_seats, first_class_seats) values (113,"2019-02-03 02:48:03", "2019-02-03 02:48:03", 140, 3500, 350, 103, 102, 122, false, 20, 20, 20);
insert into avio_flight (id,date_time_start, date_time_finish,flight_duration,flight_distance,price, start_location_id, end_location_id, avio_company_id, is_deleted, economy_class_seats, business_class_seats, first_class_seats) values (114,"2019-02-03 02:48:03", "2019-02-03 02:48:03", 150, 3500, 350, 104, 101, 122, false, 20, 20, 20);


insert into avio_flight (id,date_time_start, date_time_finish,flight_duration,flight_distance,price, start_location_id, end_location_id, avio_company_id, is_deleted, economy_class_seats, business_class_seats, first_class_seats) values (115,"2019-02-03 02:48:03", "2019-02-03 02:48:03", 120, 2000, 300, 101, 102, 121, false, 20, 20, 20);
insert into avio_flight (id,date_time_start, date_time_finish,flight_duration,flight_distance,price, start_location_id, end_location_id, avio_company_id, is_deleted, economy_class_seats, business_class_seats, first_class_seats) values (116,"2019-02-04 02:48:03", "2019-02-04 02:48:03", 120, 2000, 300, 101, 102, 122, false, 20, 20, 20);
insert into avio_flight (id,date_time_start, date_time_finish,flight_duration,flight_distance,price, start_location_id, end_location_id, avio_company_id, is_deleted, economy_class_seats, business_class_seats, first_class_seats) values (117,"2019-02-07 02:48:03", "2019-02-07 02:48:03", 120, 2000, 300, 102, 101, 121, false, 20, 20, 20);
insert into avio_flight (id,date_time_start, date_time_finish,flight_duration,flight_distance,price, start_location_id, end_location_id, avio_company_id, is_deleted, economy_class_seats, business_class_seats, first_class_seats) values (118,"2019-02-05 02:48:03", "2019-02-03 02:48:03", 120, 2000, 300, 101, 102, 122, false, 20, 20, 20);
insert into avio_flight (id,date_time_start, date_time_finish,flight_duration,flight_distance,price, start_location_id, end_location_id, avio_company_id, is_deleted, economy_class_seats, business_class_seats, first_class_seats) values (119,"2019-02-06 02:48:03", "2019-02-03 02:48:03", 120, 2000, 300, 101, 102, 121, false, 20, 20, 20);
insert into avio_flight (id,date_time_start, date_time_finish,flight_duration,flight_distance,price, start_location_id, end_location_id, avio_company_id, is_deleted, economy_class_seats, business_class_seats, first_class_seats) values (120,"2019-02-03 02:48:03", "2019-02-03 02:48:03", 120, 2000, 300, 101, 107, 122, false, 20, 20, 20);
insert into avio_flight (id,date_time_start, date_time_finish,flight_duration,flight_distance,price, start_location_id, end_location_id, avio_company_id, is_deleted, economy_class_seats, business_class_seats, first_class_seats) values (121,"2019-02-03 02:48:03", "2019-02-03 02:48:03", 120, 2000, 300, 101, 108, 121, false, 20, 20, 20);
insert into avio_flight (id,date_time_start, date_time_finish,flight_duration,flight_distance,price, start_location_id, end_location_id, avio_company_id, is_deleted, economy_class_seats, business_class_seats, first_class_seats) values (122,"2019-02-03 02:48:03", "2019-02-03 02:48:03", 120, 2000, 300, 101, 109, 122, false, 20, 20, 20);
insert into avio_flight (id,date_time_start, date_time_finish,flight_duration,flight_distance,price, start_location_id, end_location_id, avio_company_id, is_deleted, economy_class_seats, business_class_seats, first_class_seats) values (123,"2019-02-03 02:48:03", "2019-02-03 02:48:03", 120, 2000, 300, 101, 110, 121, false, 20, 20, 20);

insert into address(id, country, city, postal_code, street, number) values (105,'Srbija', 'Nis',16000, 'Ulica neka', 33);
insert into address(id, country, city, postal_code, street, number) values (106,'Srbija', 'Subotica',16000, 'Ulica neka', 33);
insert into address(id, country, city, postal_code, street, number) values (107,'Srbija', 'Kragujevac',16000, 'Ulica neka', 33);
insert into address(id, country, city, postal_code, street, number) values (108,'Srbija', 'Kraljevo',16000, 'Ulica neka', 33);
insert into address(id, country, city, postal_code, street, number) values (109,'Srbija', 'Sombor',16000, 'Ulica neka', 33);
insert into address(id, country, city, postal_code, street, number) values (110,'Srbija', 'Valjevo',16000, 'Ulica neka', 33);
insert into address(id, country, city, postal_code, street, number) values (111,'Srbija', 'Krusevac',16000, 'Ulica neka', 33);
insert into address(id, country, city, postal_code, street, number) values (112,'Srbija', 'Sabac',16000, 'Ulica neka', 33);


insert into destinations (avio_company_id, address_id) values (121, 101);
insert into destinations (avio_company_id, address_id) values (121, 102);
insert into destinations (avio_company_id, address_id) values (121, 103);
insert into destinations (avio_company_id, address_id) values (121, 104);

insert into destinations (avio_company_id, address_id) values (122, 101);
insert into destinations (avio_company_id, address_id) values (122, 102);
insert into destinations (avio_company_id, address_id) values (122, 103);
insert into destinations (avio_company_id, address_id) values (122, 104);

insert into hotel (id, name, address_id, description) values (101,'Hotel Park', 101, 'Imamo i kladionicu');
insert into hotel (id, name, address_id, description) values (102,'Hotel Vojvodina', 102, 'Nemamo ni kladionicu');

insert into hotel_menu_item (id, service_name, price, description, hotel_id) values (101,'Cistimo krevete', 99.00, 'opis opis opis',102);
insert into hotel_menu_item (id, service_name, price, description, hotel_id) values (102,'Mini bar', 25.00, 'Neki random opis', 102);
insert into hotel_menu_item (id, service_name, price, description, hotel_id) values (103,'Wellness and spa', 75.00, 'Sauna, bazeni, masaze', 102);
insert into hotel_menu_item (id, service_name, price, description, hotel_id) values (104,'Nosenje prtljaga', 10.00, 'opis opis opis',101);
insert into hotel_menu_item (id, service_name, price, description, hotel_id) values (105,'Prevoz do aerodroma', 25.00, 'Neki random opis', 101);
insert into hotel_menu_item (id, service_name, price, description, hotel_id) values (106,'Wellness and spa', 75.00, 'Sauna, bazeni, masaze', 101);


insert into hotel_room (id, number, free, hotel_id, description, price, bed_number) values (101,'106', true, 101, 'opis prve sobe', 123.99, 2);
insert into hotel_room (id, number, free, hotel_id, description, price, bed_number) values (102,'107', true, 101, 'opis druge sobe', 100.00, 2);
insert into hotel_room (id, number, free, hotel_id, description, price, bed_number) values (103,'108', true, 101, 'opis trece sobe', 149.99, 2);

insert into hotel_room (id, number, free, hotel_id, description, price, bed_number) values (104,'206', true, 102, 'opis prve sobe', 123.00, 2);
insert into hotel_room (id, number, free, hotel_id, description, price, bed_number) values (105,'207', true, 102, 'opis druge sobe', 250.00, 3);
insert into hotel_room (id, number, free, hotel_id, description, price, bed_number) values (106,'208', true, 102, 'opis trece sobe', 150.00, 2);
insert into hotel_room (id, number, free, hotel_id, description, price, bed_number) values (107,'208', true, 102, 'opis cetvrte sobe', 150.00, 4);

insert into room_reservation(id, end_reservation, start_reservation, belongs_to_room_id, user_id, price) values (102, '2019-01-21', '2019-01-15', 102, 21, 350);
insert into room_reservation(id, end_reservation, start_reservation, belongs_to_room_id, user_id, price) values (103, '2019-01-21', '2019-01-15', 103, 21, 450);
insert into room_reservation(id, end_reservation, start_reservation, belongs_to_room_id, user_id, price) values (104, '2019-01-21', '2019-01-15', 104, 21, 600);


insert into rent_car (id, name, address_id, description) values (101,'Mica rentakar', 101, 'Imamo sve.');

insert into rent_car_branch (id, name, address_id, rent_car_id, deleted) values (101, 'Micin brenc', 101, 101, 0);

insert into rent_car_menu_item (id, service_name, price, description,rent_car_id) values (101,'Pranje kola', 200,'Opis pranja kola', 101);


insert into vehicle (id, type, seats_number, name, mark, model, year_produced, rent_car_id,deleted)  values (101, 0, 5, 'Auto paja', 'Audi', 'A4', 2010, 101, 0);
insert into vehicle (id, type, seats_number, name, mark, model, year_produced, rent_car_id,deleted)  values (102, 1, 2, 'Auto paja', 'Aprilia', 'SR', 2010, 101, 0);
insert into vehicle (id, type, seats_number, name, mark, model, year_produced, rent_car_id,deleted)  values (103, 2, 2, 'Auto paja', 'Suzuki', 'SX500', 2015, 101, 0);

insert into vehicle_reservation (id, end_reseravtion, start_reservation, belongs_to_vehicle_id, return_place_id, take_place_id, user_id, vehicle_rating, rent_car_rating)  values (101, '2019-01-21', '2019-01-15', 101, 101, 101, 22, -1, 2);
insert into vehicle_reservation (id, end_reseravtion, start_reservation, belongs_to_vehicle_id, return_place_id, take_place_id, user_id, vehicle_rating, rent_car_rating)  values (102, '2019-01-29', '2019-01-26', 103, 101, 101, 22, 4, -1);
insert into vehicle_reservation (id, end_reseravtion, start_reservation, belongs_to_vehicle_id, return_place_id, take_place_id, user_id, vehicle_rating, rent_car_rating)  values (103, '2018-01-21', '2018-01-15', 101, 101, 101, 21, 3, 5);
insert into vehicle_reservation (id, end_reseravtion, start_reservation, belongs_to_vehicle_id, return_place_id, take_place_id, user_id, vehicle_rating, rent_car_rating)  values (104, '2018-01-29', '2018-01-26', 102, 101, 101, 21, 3, 4);

--insert into room_reservation (id, end_reseravtion, start_reservation, belongs_to_room_id, user_id)  values (101, '2019-01-21', '2019-01-15', 101, 22);
insert into avio_reservation (id, belongs_to_avio_id, user_id)  values (101, 103, 22);



--insert into vehicle_reservation (id, end_reseravtion, start_reservation, belongs_to_vehicle_id, return_place_id, take_place_id, user_id)  values (101, '2019-01-21', '2019-01-15', 101, 101, 101, 21);
--insert into vehicle_reservation (id, end_reseravtion, start_reservation, belongs_to_vehicle_id, return_place_id, take_place_id, user_id)  values (102, '2019-01-21', '2019-01-15', 102, 101, 101, 22);
--insert into vehicle_reservation (id, end_reseravtion, start_reservation, belongs_to_vehicle_id, return_place_id, take_place_id, user_id)  values (103, '2019-02-15', '2019-02-01', 101, 101, 101, 22);

insert into address(id, country, city, postal_code, street, number) values (101,'Srbija', 'Beograd',11000, 'Juzni Bulevar', 2);
insert into address(id, country, city, postal_code, street, number) values (102,'Srbija', 'Novi Sad',21000, 'Ulica neka', 23);
insert into address(id, country, city, postal_code, street, number) values (103,'Srbija', 'Zrenjanin',21000, 'Ulica neka', 33);
insert into address(id, country, city, postal_code, street, number) values (104,'Srbija', 'Leskovac',16000, 'Ulica neka', 33);
insert into address(id, country, city, postal_code, street, number) values (105,'Srbija', 'Nis',16000, 'Ulica neka', 33);
insert into address(id, country, city, postal_code, street, number) values (106,'Srbija', 'Subotica',16000, 'Ulica neka', 33);
insert into address(id, country, city, postal_code, street, number) values (107,'Srbija', 'Kragujevac',16000, 'Ulica neka', 33);
insert into address(id, country, city, postal_code, street, number) values (108,'Srbija', 'Kraljevo',16000, 'Ulica neka', 33);
insert into address(id, country, city, postal_code, street, number) values (109,'Srbija', 'Sombor',16000, 'Ulica neka', 33);
insert into address(id, country, city, postal_code, street, number) values (110,'Srbija', 'Valjevo',16000, 'Ulica neka', 33);
insert into address(id, country, city, postal_code, street, number) values (111,'Srbija', 'Krusevac',16000, 'Ulica neka', 33);
insert into address(id, country, city, postal_code, street, number) values (112,'Srbija', 'Sabac',16000, 'Ulica neka', 33);

