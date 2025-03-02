package edu.kpi5.dbcoursework.userhandles;

import edu.kpi5.dbcoursework.dbaccess.DBApi;
import edu.kpi5.dbcoursework.entities.coredb.*;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminHandle extends TeacherHandle{
    public AdminHandle(User user) {
        super(user);
    }

    public ArrayList<Student> kickStudents(boolean deleteAcounts, DBApi object)  {
        ArrayList<String> userCredits = new ArrayList<>();
        var kickedList = object.getKickList();
        for(var a : kickedList) {
            userCredits.add(a.getName());//брав пошук по імені, але можливо треба по чомусь іншому
        }
        if(deleteAcounts){
            object.removeUsers(userCredits);
        }
        return new ArrayList<>(kickedList);
    }
    public void removeUsers(ArrayList<String> userNames, DBApi object) {
        object.removeUsers(userNames);
    }
    public void applyScholarship(ArrayList<Student> listOfStudents, boolean increased, DBApi object) {
        object.applyScholarship(listOfStudents, increased);
    }
    public ArrayList<Student> getScholarshipList(boolean increased, DBApi object) {
        return new ArrayList<>(object.getScholarshipList(increased));
    }
    public boolean createUser(String userName, AccessLevel level, String password, DBApi object) {
        if(object.createUser(userName,level,password) == true){
            return true;
        }
        return false;
    }
    public boolean editUser(String userName, AccessLevel level, String password, DBApi object) {
        if(object.editUser(userName,level,password) == true){
            return true;
        }
        return false;
    }
    public ArrayList<Pair<String, ArrayList<Pair<Float, Float>>>> getTeachersContribution() {
        // для чого використовується ця функція ?
        return null;
    }
    public User getUser(String userName, DBApi object) {
        return object.getUser(userName);
    }
    public ArrayList<User> getUserList(DBApi object) {
        return new ArrayList<>(object.getUserList());
    }
    public void addGroup(String groupName, DBApi object) {
        try{
            object.addGroup(groupName);
        }catch (Exception e){

        }
    }
    public void editGroup(String groupName, DBApi object) {
        try{
            object.editGroup(groupName);
        }catch (Exception e){

        }
    }

    public boolean addStudentsToGroup(String groupName, ArrayList<Student> students, DBApi object){
        var studentNames = new ArrayList<String>();
        for(var a : students)
            studentNames.add(a.getLogin());
        if(object.addStudentsToGroup(groupName, studentNames)) {
            return true;
        }
        return false;
    }

    public boolean removeGroup(Long groupId, DBApi object){
        object.removeGroup(groupId);
    }

    public Group getGroup(String groupName, DBApi object){
        return object.getGroup(groupName);
    }

    public ArrayList<Group> getGroupList(DBApi object){
        return new ArrayList<>(object.getGroupList());
    }

    public List<Student> getAllStudents(DBApi object){
        return object.getAllStudents();
    }

    public List<Teacher> getAllTeachers(DBApi object){
        return object.getAllTeachers();
    }
}
