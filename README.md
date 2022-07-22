# GlobalLogic - BCI Test
*Uso
La aplicación contempla el uso de dos endpoints cuyas entradas y salidas serán detalladas a continuación:
- /sign-up
	- Se recibirá una única entrada, compuesta por el siguiente body:
	```
	{
		"name": String,
		"email": String,
		"password": String,
		"phones": [
			{
				"number": long,
				"citycode": int,
				"contrycode": String
			}
		]
	}
	```
	- Si todo sale bien, se retornará la siguiente respuesta:
	```
	{
    	"errors": [],
    	"id": String,
    	"created": String,
    	"lastLogin": String,
    	"token": String,
    	"active": boolean
	}
	```
	- Si ocurrió un error se mostrará el siguiente response:
	```
	{
		"errors": [
			{
				"timestamp": Timestamp,
				"codigo": int,
				"detail": String
			}
		]
	}
	```
- /login
	- Se recibirá un único parámetro por header. Este será el token generado previamente para el usuario.
	- Si todo sale bien, se retornará la siguiente respuesta:
		```
		{
			"id": String,
			"created": String,
			"lastLogin": String,
			"token": String,
			"isActive": boolean,
			"name": String,
			"email": String,
			"password": String,
			"phones": [
				{
					"number": long,
					"citycode": int,
					"contrycode": String
				}
			]
		}
		```
	- Si ocurrió un error se mostrará el siguiente response:
	```
	{
		"errors": [
			{
				"timestamp": Timestamp,
				"codigo": int,
				"detail": String
			}
		]
	}
	```
