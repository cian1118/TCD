package Week6;

import java.util.ArrayList;

public class Group {
    private int groupId;
    private String groupDescription;
    private ArrayList<ClubMember> groupMembers;

    public Group(int groupId, String groupDescription, ArrayList<ClubMember> groupMembers) {
        this.groupId = groupId;
        this.groupDescription = groupDescription;
        this.groupMembers = groupMembers;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public ArrayList<ClubMember> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(ArrayList<ClubMember> groupMembers) {
        this.groupMembers = groupMembers;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupid=" + groupId +
                ", groupDescription='" + groupDescription + '\'' +
                ", groupMembers=" + groupMembers +
                '}';
    }
}
