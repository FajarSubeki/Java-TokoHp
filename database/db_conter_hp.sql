-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 19, 2018 at 05:45 AM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_conter_hp`
--

-- --------------------------------------------------------

--
-- Stand-in structure for view `query_laporan`
-- (See below for the actual view)
--
CREATE TABLE `query_laporan` (
`id_kasir` varchar(15)
,`nama` varchar(30)
,`id_hp` varchar(30)
,`nama_hp` varchar(30)
,`harga_pokok` int(10)
,`harga_jual` int(10)
,`ppn` int(11)
,`diskon` int(11)
,`stok` int(8)
,`id_penjualan` varchar(15)
,`jumlah` int(3)
,`total` int(7)
,`tanggal` timestamp
,`bayar` int(8)
,`kembalian` int(8)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `query_penampung`
-- (See below for the actual view)
--
CREATE TABLE `query_penampung` (
`id_penjualan` varchar(15)
,`id_hp` varchar(15)
,`nama_hp` varchar(30)
,`harga_jual` int(10)
,`ppn` int(11)
,`diskon` int(11)
,`jumlah` int(3)
,`total` int(7)
,`tanggal` timestamp
,`id_kasir` varchar(15)
,`nama` varchar(30)
);

-- --------------------------------------------------------

--
-- Table structure for table `tb_distributor`
--

CREATE TABLE `tb_distributor` (
  `id_distributor` varchar(15) NOT NULL,
  `nama_distributor` varchar(30) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `telepon` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_distributor`
--

INSERT INTO `tb_distributor` (`id_distributor`, `nama_distributor`, `alamat`, `telepon`) VALUES
('D00001', 'PT Solusi Prima', 'Jakarta Pusat', '081298981010'),
('D00002', 'Plaza Jambu Dua', 'Bogor Utara', '081289899000'),
('D00003', 'Plaza Mangga Dua', 'Jakarta Utara', '081289990000'),
('D00004', 'PT Hp Center', 'Jakarta Pusat', '081238990900');

-- --------------------------------------------------------

--
-- Table structure for table `tb_hp`
--

CREATE TABLE `tb_hp` (
  `id_hp` varchar(30) NOT NULL,
  `nama_hp` varchar(30) NOT NULL,
  `sisop` varchar(30) NOT NULL,
  `ukuran` varchar(10) NOT NULL,
  `ram` int(10) NOT NULL,
  `rom` int(10) NOT NULL,
  `warna` varchar(10) NOT NULL,
  `kapasitas_baterai` int(10) NOT NULL,
  `rear_kamera` int(10) NOT NULL,
  `tipe_slot` varchar(20) NOT NULL,
  `jaringan` varchar(5) NOT NULL,
  `harga_pokok` int(10) NOT NULL,
  `harga_jual` int(10) NOT NULL,
  `ppn` int(11) NOT NULL,
  `diskon` int(11) NOT NULL,
  `stok` int(8) NOT NULL,
  `session` varchar(1) NOT NULL,
  `hapus` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_hp`
--

INSERT INTO `tb_hp` (`id_hp`, `nama_hp`, `sisop`, `ukuran`, `ram`, `rom`, `warna`, `kapasitas_baterai`, `rear_kamera`, `tipe_slot`, `jaringan`, `harga_pokok`, `harga_jual`, `ppn`, `diskon`, `stok`, `session`, `hapus`) VALUES
('H00007', 'Advan SG5G', 'Android Lolipop', '20', 8, 16, 'White', 4000, 20, 'Micro SD', '3G', 2000000, 2500000, 10, 8, 0, '0', '0'),
('H00008', 'Samsung Galaxy Note 5S', 'Android Oreo', '16', 4, 16, 'Pink', 4500, 19, 'Micro SD', '4G', 5000000, 5200000, 10, 5, 15, '0', '0'),
('H00010', 'Xiaomi Redmi Note 5A', 'Android Lolipop', '15', 4, 16, 'Silver', 3000, 17, 'Micro SD', '4G', 2300000, 2500000, 10, 5, 10, '0', '0'),
('H00011', 'Xiami Redmi 4A', 'Android Oreo', '15', 4, 16, 'Red', 3000, 18, 'Micro SD', '4G', 2000000, 2500000, 10, 4, 8, '0', '0'),
('H00012', 'Oppo F1', 'Android Oreo', '5.5', 4, 16, 'White', 3500, 18, 'Hybrid', '4G', 4500000, 4700000, 10, 6, 0, '0', '0'),
('H00013', 'Vivo JF1', 'Android Oreo', '5', 2, 8, 'Yellow', 3000, 17, 'Micro SD', '3G', 1600000, 1700000, 10, 4, 25, '0', '0'),
('H00014', 'Asus Zenfone', 'Android Oreo', '5.6', 4, 16, 'Pink', 3000, 17, 'Micro SD', '4G', 3500000, 3800000, 10, 4, 0, '0', '0');

-- --------------------------------------------------------

--
-- Table structure for table `tb_kasir`
--

CREATE TABLE `tb_kasir` (
  `id_kasir` varchar(15) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `telepon` varchar(20) NOT NULL,
  `status` int(1) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `akses` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_kasir`
--

INSERT INTO `tb_kasir` (`id_kasir`, `nama`, `alamat`, `telepon`, `status`, `username`, `password`, `akses`) VALUES
('1', 'Fajar Subeki', 'Citeko, Cisarua Bogor Jawa Barat', '087741280963', 1, 'admin', '61646D696E', 'admin'),
('2', 'Subeki Fajar', 'Citeko, Cisarua Bogor Jawa Barat', '085788890101', 1, 'kasir', '6B61736972', 'kasir'),
('3', 'Rafli Maulana Rizki', 'Ciawi', '081289892929', 1, 'rafli', '7261666C69', 'kasir'),
('D00001', 'Ujang', 'Bandung Lautan', '085698989999', 1, 'ujang', 'ujang', 'kasir'),
('D00002', 'Siti Rohma K', 'Cipayung', '081289890000', 1, 'siti', 'siti', 'kasir'),
('D00003', 'Mahmud', 'Cisarua', '081289990900', 1, 'mahmud', 'mahmud', 'kasir');

-- --------------------------------------------------------

--
-- Table structure for table `tb_pasok`
--

CREATE TABLE `tb_pasok` (
  `id_pasok` varchar(15) NOT NULL,
  `id_distributor` varchar(50) NOT NULL,
  `id_hp` varchar(50) NOT NULL,
  `jumlah` int(5) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_pasok`
--

INSERT INTO `tb_pasok` (`id_pasok`, `id_distributor`, `id_hp`, `jumlah`, `tanggal`) VALUES
('P00007', 'D00006', 'H00007', 2, '2018-02-11'),
('P00008', 'D00002', 'H00008', 10, '2018-02-11'),
('P00009', 'D00001', 'H00009', 5, '2018-02-11'),
('P00010', 'D00005', 'H00009', 5, '2018-02-11'),
('P00011', 'D00005', 'H00009', 10, '2018-02-11'),
('P00012', 'D00006', 'H00008', 10, '2018-02-11'),
('P00013', 'PT Electronic Digital', 'Samsung Galaxy Note 5S', 2, '2018-02-11'),
('P00014', 'PT MultiElectro', 'Samsung Galaxy Note 5S', 1, '2018-02-12'),
('P00015', 'PT MultiElectro', 'Samsung Galaxy Note 5S', 2, '2018-02-12'),
('P00016', 'PT Hp Center City', 'Samsung Galaxy Note 5S', 5, '2018-02-11'),
('P00017', 'Plaza Mangga Dua', 'Samsung Galaxy Note 5S', 2, '2018-02-12'),
('P00018', 'D00004', 'H00009', 2, '2018-02-12'),
('P00019', 'D00001', 'H00007', 8, '2018-02-12'),
('P00020', 'D00002', 'H00007', 20, '2018-02-12'),
('P00021', 'D00004', 'H00008', 30, '2018-02-12'),
('P00022', 'D00007', 'H00009', 25, '2018-02-12'),
('P00023', 'D00004', 'H00010', 20, '2018-02-12'),
('P00024', 'D00002', 'H00008', 10, '2018-02-12'),
('P00025', 'D00001', 'H00011', 20, '2018-02-13'),
('P00026', 'D00001', 'H00010', 10, '2018-02-13'),
('P00027', 'D00003', 'H00008', 10, '2018-02-13'),
('P00028', 'D00005', 'H00010', 20, '2018-02-13'),
('P00029', 'D00006', 'H00011', 15, '2018-02-13'),
('P00030', 'D00003', 'H00012', 20, '2018-02-13'),
('P00031', 'D00006', 'H00013', 30, '2018-02-13'),
('P00032', 'D00001', 'H00007', 5, '2018-02-12'),
('P00033', 'D00001', 'H00013', 5, '2018-02-15'),
('P00034', 'D00001', 'H00013', 10, '2018-02-15'),
('P00035', 'D00004', 'H00012', 20, '2018-02-16'),
('P00036', 'D00004', 'H00014', 5, '2018-02-16'),
('P00037', 'D00003', 'H00007', 10, '2018-02-17'),
('P00038', 'D00002', 'H00008', 20, '2018-02-17'),
('P00039', 'D00001', 'H00010', 20, '2018-02-17'),
('P00040', 'D00004', 'H00013', 30, '2018-02-17'),
('P00041', 'D00002', 'H00011', 20, '2018-02-17'),
('P00042', 'D00003', 'H00013', 20, '2018-02-18'),
('P00043', 'D00002', 'H00008', 5, '2018-02-18');

--
-- Triggers `tb_pasok`
--
DELIMITER $$
CREATE TRIGGER `penguranstok` AFTER DELETE ON `tb_pasok` FOR EACH ROW BEGIN INSERT INTO 
tb_hp SET id_hp= OLD.id_hp, stok=OLD.jumlah
ON DUPLICATE KEY UPDATE stok= stok-OLD.jumlah; 
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `penjumlahanstok` AFTER INSERT ON `tb_pasok` FOR EACH ROW BEGIN
INSERT INTO tb_hp SET id_hp= NEW.id_hp, stok=NEW.jumlah
ON DUPLICATE KEY UPDATE stok= stok+New.jumlah;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_penjualan`
--

CREATE TABLE `tb_penjualan` (
  `id_penjualan` varchar(15) NOT NULL,
  `id_hp` varchar(15) NOT NULL,
  `id_kasir` varchar(15) NOT NULL,
  `jumlah` int(3) NOT NULL,
  `total` int(7) NOT NULL,
  `tanggal` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_penjualan`
--

INSERT INTO `tb_penjualan` (`id_penjualan`, `id_hp`, `id_kasir`, `jumlah`, `total`, `tanggal`) VALUES
('P00001', 'H00013', '3', 2, 3502000, '2018-02-18 17:00:00'),
('P00001', 'H00010', '3', 1, 2625000, '2018-02-18 17:00:00'),
('P00002', 'H00008', '3', 1, 5460000, '2018-02-18 17:00:00'),
('P00003', 'H00013', '2', 2, 3502000, '2018-02-18 17:00:00'),
('P00004', 'H00013', '3', 2, 3502000, '2018-02-18 17:00:00'),
('P00004', 'H00010', '3', 1, 2625000, '2018-02-18 17:00:00'),
('P00005', 'H00013', '2', 2, 3502000, '2018-02-18 17:00:00'),
('P00005', 'H00008', '2', 1, 5460000, '2018-02-18 17:00:00'),
('P00006', 'H00011', '2', 2, 5150000, '2018-02-18 17:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `tb_tampung`
--

CREATE TABLE `tb_tampung` (
  `id_penjualan` varchar(15) NOT NULL,
  `id_hp` varchar(15) NOT NULL,
  `id_kasir` varchar(15) NOT NULL,
  `jumlah` int(8) NOT NULL,
  `total` int(8) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_tampung`
--

INSERT INTO `tb_tampung` (`id_penjualan`, `id_hp`, `id_kasir`, `jumlah`, `total`, `tanggal`) VALUES
('P00006', 'H00011', '2', 2, 5150000, '2018-02-19');

--
-- Triggers `tb_tampung`
--
DELIMITER $$
CREATE TRIGGER `penguranganstok` AFTER INSERT ON `tb_tampung` FOR EACH ROW BEGIN
INSERT INTO tb_hp SET id_hp= NEW.id_hp, stok=NEW.jumlah
ON DUPLICATE KEY UPDATE stok= stok-New.jumlah;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `penjumlahan` AFTER DELETE ON `tb_tampung` FOR EACH ROW BEGIN
INSERT INTO tb_hp SET id_hp= OLD.id_hp, stok=OLD.jumlah
ON DUPLICATE KEY UPDATE stok= stok+OLD.jumlah;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_uang`
--

CREATE TABLE `tb_uang` (
  `id_penjualan` varchar(15) NOT NULL,
  `id_kasir` varchar(15) NOT NULL,
  `total` int(8) NOT NULL,
  `bayar` int(8) NOT NULL,
  `kembalian` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_uang`
--

INSERT INTO `tb_uang` (`id_penjualan`, `id_kasir`, `total`, `bayar`, `kembalian`) VALUES
('P00001', '3', 6127000, 6250000, 123000),
('P00002', '3', 5460000, 5500000, 40000),
('P00003', '2', 3502000, 3502000, 0),
('P00004', '3', 6127000, 6150000, 23000),
('P00005', '2', 8962000, 8970000, 8000),
('P00006', '2', 5150000, 5150000, 0);

-- --------------------------------------------------------

--
-- Structure for view `query_laporan`
--
DROP TABLE IF EXISTS `query_laporan`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `query_laporan`  AS  select `tb_kasir`.`id_kasir` AS `id_kasir`,`tb_kasir`.`nama` AS `nama`,`tb_hp`.`id_hp` AS `id_hp`,`tb_hp`.`nama_hp` AS `nama_hp`,`tb_hp`.`harga_pokok` AS `harga_pokok`,`tb_hp`.`harga_jual` AS `harga_jual`,`tb_hp`.`ppn` AS `ppn`,`tb_hp`.`diskon` AS `diskon`,`tb_hp`.`stok` AS `stok`,`tb_penjualan`.`id_penjualan` AS `id_penjualan`,`tb_penjualan`.`jumlah` AS `jumlah`,`tb_penjualan`.`total` AS `total`,`tb_penjualan`.`tanggal` AS `tanggal`,`tb_uang`.`bayar` AS `bayar`,`tb_uang`.`kembalian` AS `kembalian` from (((`tb_hp` join `tb_penjualan` on((`tb_penjualan`.`id_hp` = `tb_hp`.`id_hp`))) join `tb_kasir` on((`tb_kasir`.`id_kasir` = `tb_penjualan`.`id_kasir`))) join `tb_uang` on((`tb_penjualan`.`id_penjualan` = `tb_uang`.`id_penjualan`))) ;

-- --------------------------------------------------------

--
-- Structure for view `query_penampung`
--
DROP TABLE IF EXISTS `query_penampung`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `query_penampung`  AS  select `tb_tampung`.`id_penjualan` AS `id_penjualan`,`tb_tampung`.`id_hp` AS `id_hp`,`tb_hp`.`nama_hp` AS `nama_hp`,`tb_hp`.`harga_jual` AS `harga_jual`,`tb_hp`.`ppn` AS `ppn`,`tb_hp`.`diskon` AS `diskon`,`tb_penjualan`.`jumlah` AS `jumlah`,`tb_penjualan`.`total` AS `total`,`tb_penjualan`.`tanggal` AS `tanggal`,`tb_kasir`.`id_kasir` AS `id_kasir`,`tb_kasir`.`nama` AS `nama` from (((`tb_tampung` join `tb_hp` on((`tb_tampung`.`id_hp` = `tb_hp`.`id_hp`))) join `tb_penjualan` on((`tb_hp`.`id_hp` = `tb_penjualan`.`id_hp`))) join `tb_kasir` on((`tb_penjualan`.`id_kasir` = `tb_kasir`.`id_kasir`))) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_distributor`
--
ALTER TABLE `tb_distributor`
  ADD PRIMARY KEY (`id_distributor`);

--
-- Indexes for table `tb_hp`
--
ALTER TABLE `tb_hp`
  ADD PRIMARY KEY (`id_hp`);

--
-- Indexes for table `tb_kasir`
--
ALTER TABLE `tb_kasir`
  ADD PRIMARY KEY (`id_kasir`);

--
-- Indexes for table `tb_pasok`
--
ALTER TABLE `tb_pasok`
  ADD PRIMARY KEY (`id_pasok`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
