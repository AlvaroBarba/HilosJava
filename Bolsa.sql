-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 28-01-2021 a las 18:07:52
-- Versión del servidor: 10.4.16-MariaDB
-- Versión de PHP: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `Bolsa`
--
CREATE DATABASE IF NOT EXISTS `Bolsa` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `Bolsa`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `apellidos` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `dni` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` int(9) NOT NULL,
  `fecha_nac` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`codigo`, `nombre`, `apellidos`, `dni`, `email`, `telefono`, `fecha_nac`) VALUES
(6, 'Pepe', 'Garcia', '12345678C', 'pepe@example.com', 987654987, '1965-01-03'),
(7, 'Julian', 'Luque', '15694264F', 'julian@example.com', 123457895, '1965-01-17'),
(8, 'Belen', 'Garcia', '47852145D', 'belen@example.com', 654875214, '1965-03-03'),
(9, 'Antonio', 'triana', '75842165R', 'antonio@example.com', 458753621, '1965-04-03'),
(10, 'Diego Ramón', 'Jimenez ', '54875326T', 'elcigala@example.com', 654821453, '1965-07-12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente_empresa`
--

CREATE TABLE `cliente_empresa` (
  `codigo_cliente` int(11) NOT NULL,
  `codigo_empresa` int(11) NOT NULL,
  `numero_acciones` int(11) NOT NULL,
  `fecha_hora_compra` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `cliente_empresa`
--

INSERT INTO `cliente_empresa` (`codigo_cliente`, `codigo_empresa`, `numero_acciones`, `fecha_hora_compra`) VALUES
(8, 15, 5, '2021-01-28 17:01:18'),
(9, 8, 15, '2021-01-28 17:01:19'),
(6, 16, 16, '2021-01-28 17:02:10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `corredor`
--

CREATE TABLE `corredor` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `apellidos` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `login` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `corredor`
--

INSERT INTO `corredor` (`codigo`, `nombre`, `apellidos`, `login`, `password`) VALUES
(1, 'Andrea', 'Díaz', 'andreadiaz@example.com', '1234'),
(2, 'Miguel Angel', 'Luque', 'migue@example.com', '1234'),
(3, 'Alvaro', 'Barba', 'alvaro@example.com', '1234');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `acciones_disponibles` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`codigo`, `nombre`, `acciones_disponibles`) VALUES
(4, 'ArreglaTodo', 439),
(5, 'Limpiamos por ti', 200),
(6, 'Real Madrid', 83),
(7, 'Famadesa', 654),
(8, 'Por la patria', 15),
(9, 'Renault', 181),
(10, 'Seat', 20),
(11, 'Xiaomi', 120),
(12, 'Youtube', 10),
(13, 'IES francisco de los rios', 1),
(14, 'Android', 255),
(15, 'Linux', 40),
(16, 'Ionic', 75),
(17, 'Odoo', 10),
(18, 'Discord', 268),
(19, 'MySql', 120),
(20, 'Clave', 100),
(22, 'Restaurante Paco Mer', 5000);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD UNIQUE KEY `codigo_cliente_dni` (`codigo`,`dni`);

--
-- Indices de la tabla `cliente_empresa`
--
ALTER TABLE `cliente_empresa`
  ADD KEY `empresa` (`codigo_empresa`),
  ADD KEY `cliente` (`codigo_cliente`);

--
-- Indices de la tabla `corredor`
--
ALTER TABLE `corredor`
  ADD UNIQUE KEY `Codigo_Corredor` (`codigo`),
  ADD UNIQUE KEY `login` (`login`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD UNIQUE KEY `codigo_empresa` (`codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `corredor`
--
ALTER TABLE `corredor`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `empresa`
--
ALTER TABLE `empresa`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente_empresa`
--
ALTER TABLE `cliente_empresa`
  ADD CONSTRAINT `cliente` FOREIGN KEY (`codigo_cliente`) REFERENCES `cliente` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `empresa` FOREIGN KEY (`codigo_empresa`) REFERENCES `empresa` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
