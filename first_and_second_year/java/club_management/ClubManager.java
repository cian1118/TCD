package Week6;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ClubManager {

    public static final int NAME_INDEX = 0;
    public static final int ADDRESS_INDEX = 1;
    public static final int YEAR_OF_REG_INDEX = 2;
    public static final int EMAIL_INDEX = 3;

    public static int groupId = 101;

    public static void main(String[] args) {
        ArrayList<ClubMember> clubMembers = new ArrayList<>();
        ArrayList<Group> groupList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("./src/Week6/members.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            boolean endOfFile = false;
            while (!endOfFile) {
                String memberInfo = bufferedReader.readLine();
                if (memberInfo == null) {
                    endOfFile = true;
                } else {
                    String[] infoArray = memberInfo.split(", ");
                    String name = infoArray[NAME_INDEX];
                    String address = infoArray[ADDRESS_INDEX];
                    int yearOfReg = Integer.parseInt(infoArray[YEAR_OF_REG_INDEX]);
                    String email = infoArray[EMAIL_INDEX];
                    ClubMember clubMember = new ClubMember(name, address, yearOfReg, email);
                    clubMembers.add(clubMember);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ClubManager manager = new ClubManager();
        boolean exit = false;
        Scanner inputScanner = new Scanner(System.in);
        while (!exit) {
            System.out.println("Please select: 1 Create group, 2 .....");
            if (inputScanner.hasNextInt()) {
                int selection = inputScanner.nextInt();

                //Create group
                if (selection==1) {
                    String groupDesc = inputScanner.next();
                    Group group = manager.createGroup(groupId, groupDesc);

                } else if (selection==2) {
                    manager.printMembers(clubMembers);
                } else if (selection==3) {
                    //change member details
                    System.out.println("Enter the name of member to change details");
                    String name = inputScanner.next();
                    ClubMember member = manager.getMember(clubMembers, name);
                    System.out.println("Enter new address of the member (or press enter to not update): ");
                    if (inputScanner.hasNext()) {
                        String address = inputScanner.next();
                        manager.updateMember(member, address, null);
                    } else {
                        System.out.println("Enter new email: ");
                        if (inputScanner.hasNext()) {
                            String email = inputScanner.next();
                            manager.updateMember(member, null, email);
                        }
                    }

                }
            }
        }
    }

    public void printMembers(ArrayList<ClubMember> clubMembers) {
        for (ClubMember member: clubMembers) {
            System.out.println(member.getName());
        }
    }

    public ClubMember getMember(ArrayList<ClubMember> clubMembers, String memberName) {
        for (int index = 0; index < clubMembers.size(); index++) {
            ClubMember member = clubMembers.get(index);
            if (member.getName().equals(memberName)) {
                return member;
            }
        }
        return null;
    }

    public void updateMember(ClubMember member, String address, String email) {
        if (member != null) {
            if (address != null) member.setAddress(address);
            if (email != null) member.setEmail(email);
        }
    }

    public Group createGroup(int groupId, String groupDescription) {
        ArrayList<ClubMember> groupMembers = new ArrayList<>();
        return new Group(groupId, groupDescription, groupMembers);
    }

    public void addMemberToGroup(Group group, ClubMember member) {
        if (!group.getGroupMembers().contains(member) && member != null) {
            group.getGroupMembers().add(member);
        }
    }

    public void printGroupMembers(Group group) {
        for (ClubMember member : group.getGroupMembers()) {
            System.out.println(member.getName());
        }
    }

    public void printGroupNamesAndNumberOfMembers(ArrayList<Group> groupList) {
        for (Group group: groupList) {
            System.out.println(group.getGroupDescription());
            System.out.println(group.getGroupMembers().size());
        }
    }



}