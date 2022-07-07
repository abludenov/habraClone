UPDATE users
SET
    username = 'Admin',
    password = '$2a$12$4h9t8zbYnGc1UbNnJ75Cb.wseqbp0QWqQpe5rw9.kxKaiPnjjfcce'
WHERE
    id = 1;

UPDATE users
SET
    username = 'User',
    password = '$2a$12$ft3fnqjiMAf6KsOze3l5uOaWhhICZo39ZA2nM8a5SrqeXuhLvtGo6'
WHERE
    id = 2;

UPDATE users
SET
    username = 'Moderator',
    password = '$2a$12$7AajYJgDGvhaLWeIbxFCyu9mPFE94beL5D8KMEgZD0V72TnYxexfi'
WHERE
    id = 3;