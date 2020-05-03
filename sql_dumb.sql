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

insert into nc_object_types
values(1, 'All', 0, 'Based Object Type');
insert into nc_object_types
values(2, 'Abstract Order Object Type', 1, 'Abstract object type for all porduct orders');
insert into nc_object_types
values(3, 'Abstract Instance Object Types', 1, 'Abstract object type for all porduct instances');
insert into nc_object_types
values(4, 'Internet Order Object Type', 2, null);
insert into nc_object_types
values(5, 'Video Order Object Type', 2, null);
insert into nc_object_types
values(6, 'Mobile Order Object Type', 2, null);
insert into nc_object_types
values(7, 'Internet Instance Object Types', 3, null);
insert into nc_object_types
values(8, 'Video Instance Object Types', 3, null);
insert into nc_object_types
values(9, 'Mobile Instance Object Type', 3, null);
insert into nc_object_types
values(10, 'Phone number', 1, null);

insert into nc_attr_type_defs
values(20, 0, null, 'For any Text attribute');
insert into nc_attr_type_defs
values(21, 2, null, 'For any Number attribute');
insert into nc_attr_type_defs
values(22, 3, null, 'For any Decimal attribute');
insert into nc_attr_type_defs
values(23, 4, null, 'For any Date attribute');
insert into nc_attr_type_defs
values(24, 6, null, 'Access Type Values');
insert into nc_attr_type_defs
values(25, 6, null, 'Service Type Values');
insert into nc_attr_type_defs
values(26, 6, null, 'Order Status Values');
insert into nc_attr_type_defs
values(27, 9, 10, 'For any Reference to Phone Number OT');

insert into  nc_list_values
values(28, 'XDSL', 24);
insert into  nc_list_values
values(29, 'GPON', 24);
insert into  nc_list_values
values(30, 'Postpaid', 25);
insert into  nc_list_values
values(31, 'Prepaid', 25);
insert into  nc_list_values
values(32, 'NA', 26);
insert into  nc_list_values
values(33, 'Entering', 26);
insert into  nc_list_values
values(34, 'Ready for processing', 26);
insert into  nc_list_values
values(35, 'Processing', 26);
insert into  nc_list_values
values(36, 'Completed', 26);
insert into  nc_list_values
values(37, 'Cancelled', 26);

insert into nc_attr_schemes
values(38, 'Designer Schema');
insert into nc_attr_schemes
values(39, 'Order Management Schema');
insert into nc_attr_schemes
values(40, 'Telecom OM Schema');

insert into nc_attributes
values(11, 'Due Date', 40, 23);
insert into nc_attributes
values(12, 'Phone Number', 40, 27);
insert into nc_attributes
values(13, 'Access Type', 40, 24);
insert into nc_attributes
values(14, 'Download Speed', 40, 20);
insert into nc_attributes
values(15, 'Service Type', 40, 25);
insert into nc_attributes
values(16, 'Suspend Reason', 40, 20);
insert into nc_attributes
values(17, 'Activation Period', 40, 21);
insert into nc_attributes
values(18, 'Product Price', 40, 22);
insert into nc_attributes
values(19, 'Order Status', 39, 26);
insert into nc_attributes
values(41, 'Created When', 38, 23);
insert into nc_attributes
values(42, 'Order completion date', 39, 23);

insert into nc_attr_object_types
values(11, 2);
insert into nc_attr_object_types
values(12, 6);
insert into nc_attr_object_types
values(13, 4);
insert into nc_attr_object_types
values(14, 4);
insert into nc_attr_object_types
values(15, 6);
insert into nc_attr_object_types
values(16, 5);
insert into nc_attr_object_types
values(17, 2);
insert into nc_attr_object_types
values(18, 2);
insert into nc_attr_object_types
values(19, 2);
insert into nc_attr_object_types
values(41, 1);

create table nc_id (id int(8));
insert into nc_id set id = 42;

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