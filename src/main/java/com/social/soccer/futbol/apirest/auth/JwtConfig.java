package com.social.soccer.futbol.apirest.auth;

public class JwtConfig {
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpQIBAAKCAQEA2wfqXY8nucfZaOiX1fCczOzCAglvmW3bVcQDd6I/yK+XzDXn\r\n" + 
			"QcLQDx2NceNLYfTif00oY8vhYcJHUP1hqNvu0hEHzdV4s2Ga4mPgFB1MssKJSfRu\r\n" + 
			"DUGQrOnpv+AQicdyPVYZd31gafc7D/83mND3pWDW6eNUB0hZt0SU8BAgL+aHlFig\r\n" + 
			"xvQzZUEUD7Ca3FkVNGoM3xrl3vQ1Gn37gUvV1roSOVMCNj3lJCrsO11Lwaw8/fAu\r\n" + 
			"u6xnnc6h28F2wFrfpjwWfkoJwu0OBxJX9b5adPNFh6iHKiLgCmSj8fszzJd2Aq4A\r\n" + 
			"nJrxKQtuy6Gy4S3hsoxUrgJI3++tFQbpY2MrKwIDAQABAoIBAAoNkvQ4XdppEdF+\r\n" + 
			"23k2UfwV4dG2HSktoaOOG5qiF8teQ9lboAGkr2gCMzvG8u7MDplJMVFjSjTyddOB\r\n" + 
			"zbmyXoPFxFXuNlwFXtaFPwOtVJff1e1PCr4RBFLraY8k+Z1ZSiUGjqStXH+AmXVi\r\n" + 
			"gWrCLJuqljKl/HVZHMUMoMlj9BY+qL2t8BwYumY5umehIYgy61+Ve+Q+EXrXT1+2\r\n" + 
			"giYtJP5M3iCqwnFfL3Vol5xSebKyrnT7Yiq10Ikn9Rt4qvkrAxmDQS5p8f7lK+ci\r\n" + 
			"mhtvwnp22dRI6ofwGv68pxC3DyPJmp3NaT4SpFqDBTXVqXsl4Y/rtbQovaIr1WP7\r\n" + 
			"t8EFggECgYEA7aTOskYAUKHQ49Ww74UfscRaWJMNSjEgOUUeHV7/QaiIdyOXIeWu\r\n" + 
			"Fcm7xu1KWA3PzrBAEdrLAo4YNIq97H8Pr+p4X5BGMxlf6BvND4TnLn9NQqBiRGAY\r\n" + 
			"mJP+Mby26KbRLe4ga669bp66uykpTbbGWkPO5oHolHfYtAyTFaKDZ2UCgYEA6/MO\r\n" + 
			"NHVOkSZ9wHOR9BQfNkncWDw6DqpbSNpSF5uM/WtP3gSjGdXet9A3kdKplqqjJB6N\r\n" + 
			"djtvapiFjbSbgJEf7fyV3kbwvWY3zbkD0McmU/DE9w/kUbH8aZqD6OZympCa18pW\r\n" + 
			"g5yDbltjygesMra2yamGe8zp5H/Lb4RF5F8Qh08CgYEApiMW4ekwZS6LdAy70ue1\r\n" + 
			"nbjRFKVRoDGx/nfEsvHr8AIbzXbTPL5uswrCvTK9S8v0IOJ1GEZmsNWYNHtehcza\r\n" + 
			"sbhxCRY7DZRKyWFOPfBDEu2qnM61hGnOKDUme60cDpA5Iywb/ExAYOqVTy1ak9sY\r\n" + 
			"1pgbOGlPqSQUGuPDnFduGEUCgYEAya6pvrdi3/mJ1f4zjPHCODJsUVOxwKVnf991\r\n" + 
			"+k3w9xFKZJmMHOJODMrDJTdv1KSo7I/dFWn6c6BJ0HXH0d667z9Pj38N+qcsHgH+\r\n" + 
			"u+/u2CNfCVwhKp+8BTyZcnPBFFN0t+1xg/A9/A/MPX3PeE3r8nagyKPAbZu69eiX\r\n" + 
			"oXyM8UsCgYEAof89c0V3HtsiEBTmRxQhLRgpnuv5OLbiSeIIDSY9m5f452bIbG5Z\r\n" + 
			"JyIgq1nLfEXRlokCQSM6YLNNXWULy6l0KrBlljyRG9Blgrd+Ugnqxrt2es9tZ177\r\n" + 
			"t6lU4DIovmdWUwAKCJFwEvZnh5Kk2htSUJa9nKssU/cPCr5jFUfNTjk=\r\n" + 
			"-----END RSA PRIVATE KEY-----\r\n";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2wfqXY8nucfZaOiX1fCc\r\n" + 
			"zOzCAglvmW3bVcQDd6I/yK+XzDXnQcLQDx2NceNLYfTif00oY8vhYcJHUP1hqNvu\r\n" + 
			"0hEHzdV4s2Ga4mPgFB1MssKJSfRuDUGQrOnpv+AQicdyPVYZd31gafc7D/83mND3\r\n" + 
			"pWDW6eNUB0hZt0SU8BAgL+aHlFigxvQzZUEUD7Ca3FkVNGoM3xrl3vQ1Gn37gUvV\r\n" + 
			"1roSOVMCNj3lJCrsO11Lwaw8/fAuu6xnnc6h28F2wFrfpjwWfkoJwu0OBxJX9b5a\r\n" + 
			"dPNFh6iHKiLgCmSj8fszzJd2Aq4AnJrxKQtuy6Gy4S3hsoxUrgJI3++tFQbpY2Mr\r\n" + 
			"KwIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
		
}
