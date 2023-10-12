# Internship-Portal-App
Spring-Boot project to manage job applications.
One can register as an "employer" to post and delete jobs
One can register as an "applicant" to apply to existing jobs

Authentication and Authorization is done using Jwt tokens.

To run the application postgres must be installed locally and a db called "InternshipPortal" must be created for user postgres.

The database structure looks like this:
![dbDiagram](https://github.com/mihaitataru/Internship-Portal-App/assets/96471978/4b027840-a110-4ffd-aa7f-61adfa2a618b)



Using the "/api/v1/auth/register" endpoint a user can be created and stored in the database. After that using the "/api/v1/auth/authenticate" the user can "log in" and get his auth token which can be used as authorization for every other endpoints.

To add a new job listing one must authenticated as an employer and the use the "api/v1/jobs/add" endpoint with the body containing data about the new job
![image](https://github.com/mihaitataru/Internship-Portal-App/assets/96471978/04308f7d-3b1d-4c97-a20c-e93f24427dcc)



To display a list of all jobs the "api/v1/jobs" can be used:
![image](https://github.com/mihaitataru/Internship-Portal-App/assets/96471978/b96ca68b-9f87-4451-a71d-274a61850351)



To display a list of all jobs from a specific employer the name of the employer must be specificied at the end of the same endpoint as path variable:
![image](https://github.com/mihaitataru/Internship-Portal-App/assets/96471978/2512c423-7406-40ef-9d20-60f0bb7e42be)
![image](https://github.com/mihaitataru/Internship-Portal-App/assets/96471978/2fcf0fe0-d37e-4b27-8022-dcffbaa710de)



To delete a job listing an employer can use the endpoint "api/v1/jobs/remove/{jobId}" the path variable representing the Id of the job to be deleted
![image](https://github.com/mihaitataru/Internship-Portal-App/assets/96471978/dbb4356a-4806-471d-9b28-47d532df060f)



To apply for a job one must be authenticated as an applicant and use the "api/v1/jobs/apply" endpoint. The body of the request must contain all the relevant data about the applicant as displayed below. All the data is validated and if a part is missing or is wrong the server sends a message displaying what went wrong when creating the application.
![image](https://github.com/mihaitataru/Internship-Portal-App/assets/96471978/a6451be3-4053-4cee-aebb-2bda923b98b9)

![image](https://github.com/mihaitataru/Internship-Portal-App/assets/96471978/ce9b4942-abd7-460f-affe-35c4f0b0cba6)



To display a list of all applicants for a specific job or employer the "/api/v1/jobs/applicants/{var}" endpoint can be used, where "var" is either the name of the employer or the id of the job.
![image](https://github.com/mihaitataru/Internship-Portal-App/assets/96471978/81eecc89-ac3d-42b9-851f-ac21bd5e7f71)

![image](https://github.com/mihaitataru/Internship-Portal-App/assets/96471978/b6427527-d6c0-4f86-9d4d-26ad46903c5c)

![image](https://github.com/mihaitataru/Internship-Portal-App/assets/96471978/b7e43bd5-b6ad-4102-aa52-9a75f7b4544f)








