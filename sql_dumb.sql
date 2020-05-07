create database opf;

use opf;

create table nc_object_types(
	object_type_id int unsigned auto_increment primary key,
    name varchar(50) not null,
    parent_id int unsigned not null,
    description varchar(200));

create table nc_objects(
	object_id int unsigned auto_increment primary key,
    name varchar(50) not null,
    object_type_id int unsigned,
    description varchar(200),
	foreign key (object_type_id) references nc_object_types(object_type_id));

create table nc_attr_type_defs(
	attr_type_def_id int unsigned auto_increment primary key,
    type int not null,
    object_type_id int unsigned,
    description varchar(200),
    foreign key (object_type_id) references nc_object_types(object_type_id));

create table nc_list_values(
	list_value_id int unsigned auto_increment primary key,
    value varchar(50) not null,
    attr_type_def_id int unsigned,
    foreign key (attr_type_def_id) references nc_attr_type_defs(attr_type_def_id));

create table nc_attr_schemes(
    attr_schema_id int unsigned auto_increment primary key,
    name varchar(50) not null);

create table nc_attributes(
    attr_id int unsigned auto_increment primary key,
    name varchar(50),
    attr_schema_id int unsigned,
    attr_type_def_id int unsigned,
    foreign key (attr_type_def_id) references nc_attr_type_defs(attr_type_def_id),
    foreign key (attr_schema_id) references nc_attr_schemes(attr_schema_id));

create table nc_attr_object_types(
    attr_id int unsigned,
    object_type_id int unsigned,
    foreign key (attr_id) references nc_attributes(attr_id),
    foreign key (object_type_id) references nc_object_types(object_type_id));

create table nc_references(
	object_id int unsigned,
    attr_id int unsigned,
    reference int unsigned,
    foreign key (object_id) references nc_objects(object_id),
    foreign key (attr_id) references nc_attributes(attr_id),
    unique key (object_id, attr_id));

create table nc_params(
	object_id int unsigned not null,
    attr_id int unsigned not null,
    list_value_id int unsigned,
	value varchar(50),
	foreign key (object_id) references nc_objects(object_id),
    foreign key (attr_id) references nc_attributes(attr_id),
    foreign key (list_value_id) references nc_list_values(list_value_id),
    unique key (object_id, attr_id));

/*
insert into nc_attr_type_defs values(object_type_id, name, parent_id, description);
*/
insert into nc_object_types values(1, 'All', 0, 'Based Object Type');
insert into nc_object_types values(10, 'Abstract Order Object Type', 1, 'Abstract Object Type for all Orders');
insert into nc_object_types values(11, 'New Order Object Type', 10, null);
insert into nc_object_types values(12, 'Modify Order Object Type', 10, null);
insert into nc_object_types values(13, 'Disconnect Order Object Type', 10, 'Abstract Object Type for all Orders');
insert into nc_object_types values(20, 'New Internet Order Object Type', 11, null);
insert into nc_object_types values(21, 'Modify Internet Order Object Type', 12, null);
insert into nc_object_types values(22, 'Disconnect Internet Order Object Type', 13, null);
insert into nc_object_types values(23, 'New Video Order Object Type', 11, null);
insert into nc_object_types values(24, 'Modify Video Order Object Type', 12, null);
insert into nc_object_types values(25, 'Disconnect Video Order Object Type', 13, null);
insert into nc_object_types values(26, 'New Mobile Order Object Type', 11, null);
insert into nc_object_types values(27, 'Modify Mobile Order Object Type', 12, null);
insert into nc_object_types values(28, 'Disconnect Mobile Order Object Type', 13, null);
insert into nc_object_types values(50, 'Abstract Instance Object Types', 1, 'Abstract Object Type for all Instances');
insert into nc_object_types values(51, 'Internet Instance Object Types', 50, null);
insert into nc_object_types values(52, 'Video Instance Object Types', 50, null);
insert into nc_object_types values(53, 'Mobile Instance Object Type', 50, null);
insert into nc_object_types values(80, 'Phone Number', 1, 'Phone Number Object Type');

/*
insert into nc_attr_type_defs values(attr_type_def_id, type, object_type_id, description);
*/
insert into nc_attr_type_defs values(300, 0, null, 'For any Text attribute');
insert into nc_attr_type_defs values(301, 2, null, 'For any Number attribute');
insert into nc_attr_type_defs values(302, 3, null, 'For any Decimal attribute');
insert into nc_attr_type_defs values(303, 4, null, 'For any Date attribute');
insert into nc_attr_type_defs values(304, 6, null, 'Access Type Values');
insert into nc_attr_type_defs values(305, 6, null, 'Service Type Values');
insert into nc_attr_type_defs values(306, 6, null, 'Order Status Values');
insert into nc_attr_type_defs values(307, 6, null, 'Order Aim Values');
insert into nc_attr_type_defs values(308, 9, 80, 'For any Reference to Phone Number OT');
insert into nc_attr_type_defs values(309, 6, null, 'Provider Values');
insert into nc_attr_type_defs values(310, 9, 10, 'For any Reference to Order OT');

/*
insert into nc_list_values values(list_value_id, value, attr_type_def_id);
*/
insert into  nc_list_values values(401, 'XDSL', 304);
insert into  nc_list_values values(402, 'GPON', 304);
insert into  nc_list_values values(403, 'Postpaid', 305);
insert into  nc_list_values values(404, 'Prepaid', 305);
insert into  nc_list_values values(405, 'NA', 306);
insert into  nc_list_values values(406, 'Entering', 306);
insert into  nc_list_values values(407, 'Ready for processing', 306);
insert into  nc_list_values values(408, 'Processing', 306);
insert into  nc_list_values values(409, 'Completed', 306);
insert into  nc_list_values values(410, 'Cancelled', 306);
insert into  nc_list_values values(411, 'Superseded', 306);
insert into  nc_list_values values(412, 'New', 307);
insert into  nc_list_values values(413, 'Modify', 307);
insert into  nc_list_values values(414, 'Disconnect', 307);
insert into  nc_list_values values(415, 'HBO', 309);
insert into  nc_list_values values(416, 'Netflix', 309);

/*
insert into nc_attr_schemes values(attr_schema_id, name);
*/
insert into nc_attr_schemes values(500, 'Designer Schema');
insert into nc_attr_schemes values(501, 'Order Management Schema');
insert into nc_attr_schemes values(502, 'Telecom OM Schema');

/*
insert into nc_attributes values(attr_id, name, attr_schema_id, attr_type_def_id);
*/
insert into nc_attributes values(601, 'Due Date', 502, 303);
insert into nc_attributes values(602, 'Phone Number', 502, 308);
insert into nc_attributes values(603, 'Access Type', 502, 304);
insert into nc_attributes values(604, 'Download Speed', 502, 300);
insert into nc_attributes values(605, 'Service Type', 502, 305);
insert into nc_attributes values(606, 'Disconnect Reason', 502, 300);
insert into nc_attributes values(607, 'Activation Period', 502, 301);
insert into nc_attributes values(608, 'Product Price', 502, 302);
insert into nc_attributes values(609, 'Order Status', 501, 306);
insert into nc_attributes values(610, 'Order Aim', 501, 307);
insert into nc_attributes values(611, 'Created When', 500, 303);
insert into nc_attributes values(612, 'Order Completion Date', 501, 303);
insert into nc_attributes values(613, 'Provider', 502, 309);
insert into nc_attributes values(614, 'Previous Order', 501, 310);

/*
insert into nc_attr_object_types values(attr_id, object_type_id);
*/
insert into nc_attr_object_types values(601, 11);
insert into nc_attr_object_types values(601, 12);
insert into nc_attr_object_types values(602, 26);
insert into nc_attr_object_types values(602, 27);
insert into nc_attr_object_types values(603, 20);
insert into nc_attr_object_types values(603, 21);
insert into nc_attr_object_types values(604, 20);
insert into nc_attr_object_types values(604, 21);
insert into nc_attr_object_types values(605, 26);
insert into nc_attr_object_types values(605, 27);
insert into nc_attr_object_types values(606, 13);
insert into nc_attr_object_types values(607, 11);
insert into nc_attr_object_types values(607, 12);
insert into nc_attr_object_types values(608, 11);
insert into nc_attr_object_types values(608, 12);
insert into nc_attr_object_types values(609, 10);
insert into nc_attr_object_types values(610, 10);
insert into nc_attr_object_types values(611, 1);
insert into nc_attr_object_types values(612, 10);
insert into nc_attr_object_types values(613, 23);
insert into nc_attr_object_types values(613, 24);
insert into nc_attr_object_types values(614, 13);

create table nc_id (id int(8));
insert into nc_id set id = 1000;

delimiter $$
create function generateId ()
    returns int
    begin
        declare res int(8);
        update nc_id set id = id + 1;
        select id into res from nc_id;
        return res;
    end$$
delimiter ;