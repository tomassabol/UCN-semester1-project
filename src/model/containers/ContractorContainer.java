package model.containers;

import java.util.ArrayList;
import java.util.List;

import model.Contractor;

public class ContractorContainer {
    private static ContractorContainer instance;
    private ArrayList<Contractor> contractors;

    /**
     * Constructor class ContractorContainer
     */
    private ContractorContainer() {
        contractors = new ArrayList<>();
    }

    /**
     * get instance of object ContractorContainer
     * @return instance
     */
    public static ContractorContainer getInstance() {
        if (instance == null) {
            instance = new ContractorContainer();
        }
        return instance;
    }

    /**
     * @param contractor to be added to the container
     * @return true if successfully added
     */
    public boolean addContractor(Contractor contractor) {
        return contractors.add(contractor);
    }

    /**
     * @return list of all the contractors in ArrayList
     */
    public List<Contractor> getContractors() {
        return this.contractors;
    }

    /**
     * @param contractorId - id of a contractor you are searching for
     * @return contractor if found
     */
    public Contractor findContractorById(int contractorId) {
        for (Contractor contractor : contractors) {
            if (contractor.ID == contractorId) {
                return contractor;
            }
        }
        return null;
    }

    /**
     * @param contractor to be removed from ArrayList
     * @return true if successfully removed
     */
    public boolean removeContractor(Contractor contractor) {
        return contractors.remove(contractor);
    }

}
