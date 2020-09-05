CREATE TABLE employee(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
	last_name VARCHAR(255),
	gender CHAR(1),
	email VARCHAR(255)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



CREATE TABLE department(
	id int(11) primary key auto_increment,
	name varchar(255)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

ALTER TABLE employee ADD COLUMN department_id int(11);

ALTER TABLE employee ADD CONSTRAINT fk_employee_department
FOREIGN KEY(department_id) REFERENCES department(id);

INSERT INTO department(name) values ('开发部');
INSERT INTO department(name) values ('测试部');


ALTER TABLE employee ADD COLUMN abc VARCHAR(255) DEFAULT '';
ALTER TABLE employee DROP COLUMN abc;
