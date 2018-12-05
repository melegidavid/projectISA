insert into avio_company (id,name, address,description,average_rating) values (1,'AirSerbia', 'Beograd 34', 'etihad uzo', 2.0);
insert into avio_company (id,name, address,description,average_rating) values (2,'AirFrance', 'Paris 35', 'etihad nije uzo', 5.0);

insert into avio_flight (id,date_time_start, date_time_finish,flight_duration,flight_distance,number_of_transfers,price) values (1,'1 januar', '2 januar', 120, 2000, 2, 300);
insert into avio_flight (id,date_time_start, date_time_finish,flight_duration,flight_distance,number_of_transfers,price) values (2,'1 jun', '3 jun', 111, 3500, 1, 350);

insert into hotel (id, name, address, description) values (1,'Hotel Park', 'Novosadskog sajma 4', 'Imamo i kladionicu');
insert into hotel (id, name, address, description) values (2,'Hotel Vojvodina', 'Petrovgrad', 'Nemamo ni kladionicu');

insert into hotel_menu_item (id, service_name, price, description) values (1,'Cistimo krevete', 99.99, 'A mozete i sami');

insert into hotel_room (id, number, reserved, free, hotel) values (1,'106', false, true, 1);

insert into rent_car (id, name, address, description) values (1,'Mica rentakar', 'Leskovac 2', 'Imamo sve.');

insert into rent_car_branch (id, name, city, country, street, postal_code) values (1,'Micin brenc', 'Leskovac','Srbija','Ulica 11' ,'16000');

