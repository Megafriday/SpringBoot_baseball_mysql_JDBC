CREATE TABLE IF NOT EXISTS baseball.player (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50),
	age INT,
	team VARCHAR(50),
	position VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS baseball.user (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) not null,
    authorities VARCHAR(100) not null,
    account_non_locked BOOLEAN DEFAULT true,
    account_non_expired BOOLEAN DEFAULT true,
    credentioals_non_expird BOOLEAN DEFAULT true,
    enabled BOOLEAN DEFAULT true
);


/* 従業員テーブル（第３章で作成） */
/*
CREATE TABLE IF NOT EXISTS employee (
    employee_id INT PRIMARY KEY,
    employee_name VARCHAR(50),
    age INT
);
*/

/* ユーザーマスタ */
/*
CREATE TABLE IF NOT EXISTS m_user (
    user_id VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100),
    user_name VARCHAR(50),
    birthday DATE,
    age INT,
    marriage BOOLEAN,
    role VARCHAR(50)
);
*/