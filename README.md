Solinor Wage Calculator

Application should allow the user to calculate salarie for employees.
Current worked hours for employees are stored in the CSV. 
CSV file contains Name, Id, Date, Start Time and End Time.
There are different rates that are paid to the user such as evening rate, standard rate, overtime rates.

Spring boot application is created which uses Java, OpenCSV, lombok etc and Thymeleaf with Bootstrap for the UI.
Bootstrap UI will make the application easier to view on tablet devices etc as well as desktops.
To read the CSV files the OpenCSV library was used which maps the CSV file to the object.
We let the user to upload the file which will be stored by default at /etc/solinor/csv if the folder doesn't exist application will create it.
This path can be changed in the webserver.properties. 
webserver.properties are in the /etc/solinor folder If webserver.properties are not found the default properties from resources will be user. 

Webserver.properties also include the pay rate such as standard salary of 4.25 etc. This will insure that if rates will change in future we can just change the property. 

The user after uploading the file will be shown the salary calculations, and user can also remove the file or choose to keep it and remove it later if wanted.

The logo for the application is owned by "Solinor" and application is only uses it for demonstration pursposes for the task that is given by Solinor.

Few things are missing and should be added for the fully working and usable application
1)Security - currently we don't ask the user to authenticate. This can be done with spring security and we can store the user information on the database.
2)Database - currently we don't have a database, but it would be nice to store the csv data in the database so we could manipulate the data better, as well as store the data for the period of time that its needed. Data can be removed by the user or scheduler if needed.
3)Tests currently there are just few tests which tests some of the logic of the application. There should be some more tests such as integration tests.
4)Application currently doesn't ask the user to confirm the deletion of the CSV file
5)Standard hour/overtime hours/evening hours should be also configurable as maybe in future evening hours would be from 8pm?
6)There is no checking if the file is CSV file.
