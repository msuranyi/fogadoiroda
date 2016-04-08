drop user fogadoiroda cascade;

CREATE USER fogadoiroda IDENTIFIED BY fogadoiroda PROFILE DEFAULT ACCOUNT UNLOCK;
GRANT CONNECT TO fogadoiroda;
GRANT RESOURCE TO fogadoiroda;
GRANT UNLIMITED TABLESPACE TO fogadoiroda;