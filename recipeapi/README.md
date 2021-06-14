Recipe API 
-----
## Running the application

Clone the repository to your local 

```
git clone https://github.com/nandakishorek69/recipeapi.git

```

Go to root directory of the code. Run following command to run the application. 

```
mvn clean install

```

Now the application is build to run. Use following command to run spring boot application on command prompt.

```
mvn spring-boot:run

```

In case, If you want to run the application directly from IDE. Import the project to your favorite IDE as 'Existing maven project'. Select project from the IDE and run it as java application. “Don't forget to update maven ;)” I have configured the port to 8882 in properties. So the application can be accessed by `http://localhost:8882`

Application is using mysql database. Following is the details of connection. 
- `http://localhost:2020`
- spring.jpa.hibernate.ddl-auto=update
- spring.datasource.url=jdbc:mysql://localhost:3306/abndev
- spring.datasource.username=root
- spring.datasource.password=****

## API details

- Create Recipe
	- Create recipe service is used to create new recipe. Following is the api url and sample json. It is POST request which accepts JSON body for recipe.
	- `http://localhost:2020/api/recipe`
	
```	
	  {
	  "dishType":"NV",
	  "numOfPeople":4,
	  "ingredients":["Rice","Chicken"],
	  "cookingInstruction":"Spicy"
	  }	
	  
```

- Update Recipe
	- Update recipe service is used to update existing recipe. Following is the api url and sample json. It is PUT request which accept JSON body for recipe alongwith ID as path parameter. 
	- `http://localhost:2020/api/recipe/19`
	
```
	  {
	  "dishType":"Veg",
	  "numOfPeople":4,
	  "ingredients":["Rice","veges"],
	  "cookingInstruction":"Spicy"
	  }
	  
```

- Find Recipe By Id
	- Find the recipe by id can be used to fetch particular Recipe. Following is the api url. It is GET request which accept ID as path parameter.
	- `http://localhost:2020/api/recipe/19`
	
```	
		GET 
		'Accept: application/json' 
		'/api/recipe/{id}'

```
	
- Delete Recipe By Id
	- Delete the recipe by id can be used to delete particular Recipe. Following is the api url. It is DELETE request which accept ID as path parameter.
	- `http://localhost:2020/api/recipe/19`
	
```	
		GET 
		'Accept: application/json' 
		'/api/recipe/{id}'

```

