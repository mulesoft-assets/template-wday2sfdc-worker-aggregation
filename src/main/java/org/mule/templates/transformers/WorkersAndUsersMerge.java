/**
 * Mule Anypoint Template
 * Copyright (c) MuleSoft, Inc.
 * All rights reserved.  http://www.mulesoft.com
 */

package org.mule.templates.transformers;

import java.util.*;

/**
 * This transformer will take to list as input and create a third one that will be the merge of the previous two.
 * The identity of an element of the list is defined by its email.
 *
 * @author aurel.medvegy
 */
public class WorkersAndUsersMerge {

    public static final String WORKDAY_WORKERS = "workersFromWorkday";
    public static final String SALESFORCE_USERS = "usersFromSalesforce";

    /**
     * The method will merge the users from the two lists creating a new one.
     *
     * @param workersFromWorkday  users from Workday
     * @param usersFromSalesforce users from Salesforce
     * @return a list with the merged content of the to input lists
     */
    List<Map<String, String>> mergeList(List<Map<String, String>> workersFromWorkday, List<Map<String, String>> usersFromSalesforce) {

        List<Map<String, String>> mergedUsersList = new ArrayList<Map<String, String>>();

        // Put all users from Workday in the merged mergedUsersList
        for (Map<String, String> userFromWorkday : workersFromWorkday) {
            Map<String, String> mergedUser = createMergedUser(userFromWorkday);
            mergedUser.put("IDInWorkday", userFromWorkday.get("Id"));
            mergedUser.put("WorkerNameInWorkday", userFromWorkday.get("Username"));
            mergedUsersList.add(mergedUser);
        }

        // Add the new users from Salesforce and update the exiting ones
        for (Map<String, String> userFromSalesforce : usersFromSalesforce) {
            Map<String, String> userFromWorkday = findUserInList(userFromSalesforce.get("Email"), mergedUsersList);
            if (userFromWorkday != null) {
                userFromWorkday.put("IDInSalesforce", userFromSalesforce.get("Id"));
                userFromWorkday.put("UserNameInSalesforce", userFromSalesforce.get("Username"));
            } else {
                Map<String, String> mergedUser = createMergedUser(userFromSalesforce);
                mergedUser.put("IDInSalesforce", userFromSalesforce.get("Id"));
                mergedUser.put("UserNameInSalesforce", userFromSalesforce.get("Username"));
                mergedUsersList.add(mergedUser);
            }
        }
        return mergedUsersList;
    }

    private Map<String, String> createMergedUser(Map<String, String> user) {

        Map<String, String> mergedUser = new HashMap<String, String>();
        mergedUser.put("Email", user.get("Email"));
        mergedUser.put("Name", user.get("Name"));
        mergedUser.put("IDInWorkday", "");
        mergedUser.put("WorkerNameInWorkday", "");
        mergedUser.put("IDInSalesforce", "");
        mergedUser.put("UserNameInSalesforce", "");
        return mergedUser;
    }

    private Map<String, String> findUserInList(String email, List<Map<String, String>> userList) {

        for (Map<String, String> user : userList) {
            if (user.get("Email").equals(email)) {
                return user;
            }
        }
        return null;
    }
}
