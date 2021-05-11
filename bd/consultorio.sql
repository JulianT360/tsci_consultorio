DROP DATABASE consultorio;

CREATE DATABASE consultorio;
USE consultorio;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `persona` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(255) NOT NULL,
    `ap_paterno` VARCHAR(255) NOT NULL,
    `ap_materno` VARCHAR(255),
    `sexo` VARCHAR(50) NOT NULL, 
    `edad` INTEGER NULL,
    `telefono` VARCHAR(14) NULL,
    `celular` VARCHAR(14) NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `pais` (
	`id` INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `pais` VARCHAR(255) NOT NULL
)  ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `estado` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
    `estado` VARCHAR(255) NOT NULL,
    `paisId` INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (paisId) REFERENCES pais(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `municipio` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
    `municipio` VARCHAR(255) NOT NULL,
    `estadoId` INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (estadoId) REFERENCES estado(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `direccion` (
	`id` INTEGER NOT NULL AUTO_INCREMENT,
    `personaId` INTEGER NOT NULL,
    `calle` VARCHAR(255) NOT NULL,
    `numInt` VARCHAR(12),
    `numExt` VARCHAR(12) NOT NULL,
    `municipioId` INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (personaId) REFERENCES persona(id),
    FOREIGN KEY (municipioId) REFERENCES municipio(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `paciente` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `personaId` INTEGER NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (personaId) REFERENCES persona(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `medico` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `cedula` INTEGER NOT NULL,
  `especialidad` varchar(255) NOT NULL,
  `categoria` varchar(255) NOT NULL,
  `usuario` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `personaId` INTEGER NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (personaId) REFERENCES persona(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `antecedente_clinico` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `tipo` varchar(40) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `pacienteId` INTEGER NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (pacienteId) REFERENCES paciente(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `medicamento` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `cantidad` INTEGER NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `receta_medica` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `expediente` INTEGER NOT NULL,
  `fecha` datetime NOT NULL,
  `altura` INTEGER NOT NULL,
  `peso` INTEGER NOT NULL,
  `temperatura` INTEGER NOT NULL,
  `presion_i` INTEGER NOT NULL,
  `presion_f` INTEGER NOT NULL,
  `ritmo_c` INTEGER NOT NULL,
  `motivo_consulta` TEXT NOT NULL,
  `sintomas` TEXT NOT NULL,
  `instrucciones` TEXT,
  `medicoId` INTEGER NOT NULL,
  `pacienteId` INTEGER NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (medicoId) REFERENCES medico(id),
  FOREIGN KEY (pacienteId) REFERENCES paciente(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `detalle_rm` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `receta_medicaId` INTEGER NOT NULL,
  `medicamentoId` INTEGER NOT NULL,
  `cantidad` INTEGER NOT NULL,
  `frecuencia` INTEGER NOT NULL,
  `tomar` VARCHAR(255) NOT NULL,
  `durante` VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (receta_medicaId) REFERENCES receta_medica(id),
  FOREIGN KEY (medicamentoId) REFERENCES medicamento(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

USE consultorio;

insert into persona (nombre, ap_paterno, ap_materno, sexo, edad, telefono, celular) 
	values ('Julian', 'Tovar', 'Delgado', 'MASCULINO', 23, '8181400076', '8117140953');

insert into persona (nombre, ap_paterno, ap_materno, sexo, edad, telefono, celular) 
	values ('Juan', 'Tovar', 'Delgado', 'MASCULINO', 23, '8181400076', '8117140953');

insert into Medico (cedula, especialidad, categoria, usuario, password, personaId)
	values ('123456', 'MEDICO GRAL', 'DOCTOR', 'jtovar', 'test', 3);
    
insert into Medico (cedula, especialidad, categoria, usuario, password, personaId)
	values ('123456', 'ADMINISTRADOR', 'ADMINISTRADOR', 'admin', 'admin', 4);