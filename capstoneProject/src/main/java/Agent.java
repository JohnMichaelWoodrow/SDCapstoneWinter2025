/**
 * This is just some starter/brainstorming type stuff, feel free to change anything as needed
 */

public class Agent {
    private int agentID;
    private String agentName;
    private String agentRole;

    public Agent(int agentID, String agentName, String agentRole) {
        this.agentID = agentID;
        this.agentName = agentName;
        this.agentRole = agentRole;
    }
    public int getAgentID() {
        return agentID;
    }

    public String getAgentName() {
        return agentName;
    }

    public String getAgentRole() {
        return agentRole;
    }

    public void viewCustomerInfo() {
        //TODO: View Customer info logic?
    }

    public void updateCustomerPolicy() {
        //TODO: Update Customer Policy logic?
    }
}
