package app.algorithms;

import java.util.List;

import app.model.Employee;

public class Searcher {

    // Recursive Binary Search: finds an employee by name in a list sorted by name.
    // Returns the employee, or null if no employee has that name.
    public Employee binarySearch(List<Employee> sortedEmployees, String targetName, int left, int right) {

        // base case: the search area is empty, so the name is not in the list
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        Employee middleEmployee = sortedEmployees.get(mid);
        int comparison = targetName.compareToIgnoreCase(middleEmployee.getName());

        if (comparison == 0) {
            return middleEmployee; // base case: found it
        } else if (comparison < 0) {
            return binarySearch(sortedEmployees, targetName, left, mid - 1); // search in the left half
        } else {
            return binarySearch(sortedEmployees, targetName, mid + 1, right); // search in the right half
        }
    }
}
