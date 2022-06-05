# STUDENT MANAGEMENT IN CONSOLE

## STEPS TO GET STARTED
- You can download the jar file at `out/artifacts/StudentManagementConsole.jar`
then run this on your terminal.

```java
java StudentManagementConsole.jar
```

- OR **clone the repository** and then run `Main.java`

```git 
git clone https://github.com/Jiseeeh/StudentManagementConsole.git
```

### After running
- Login using the **admin** account
- `username: admin`
- `password: admin`

---

# What this console app can do
- Create **students** or **teachers** using the admin account or manually adding in the CSV file that will be generated.
- Give feedbacks/tasks to students as teacher (will not be saved when the app exits)
- See feedbacks/tasks from teachers as a student (will not be saved when the app exits)

# What this console app cannot do yet
- Make the feedbacks/tasks ***link to a specific student and a teacher object*** because as of now the feedbacks and tasks are visible to all 
students/teachers.
- Save feedbacks/tasks into a CSV file and when the user runs the app again, the feedbacks/tasks will be loaded.

# Contribution
- You can just make a pull request, it is highly appreciated! As of now I cannot seem to find a way to add the saving of feedbacks/tasks maybe because my
code structure is not that good.
