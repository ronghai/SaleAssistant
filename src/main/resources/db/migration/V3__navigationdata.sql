
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";



--
-- 转存表中的数据 `navigations`
--

INSERT INTO `navigations` (`id`, `tier_1`, `tier_2`, `tier_3`, `tier_4`, `worker`, `label`, `order`, `disabled`, `add_time`, `update_time`, `note`) VALUES
(1, 1, 0, 0, 0, 'com.ecbeta.app.engine.worker.HomeWorker', '主页', 0, 0, NULL, NULL, NULL),
(2, 1, 0, 1, 0, 'com.ecbeta.app.engine.worker.ClientWorker', '客户', 0, 0, NULL, NULL, NULL);

