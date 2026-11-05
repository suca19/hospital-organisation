package app.algorithms;

import java.util.ArrayList;
import java.util.List;

import app.model.Employee;

public class Sorter {

    // Recursive Merge Sort: sorts employees alphabetically by name (ignoring case)
    public List<Employee> mergeSort(List<Employee> employees) {

        // base case: a list with 0 or 1 employees is already sorted
        if (employees.size() <= 1) {
            return employees;
        }

        // split the list into two new halves
        int mid = employees.size() / 2;
        List<Employee> left = new ArrayList<>(employees.subList(0, mid));
        List<Employee> right = new ArrayList<>(employees.subList(mid, employees.size()));

        // recursive step: sort each half
        List<Employee> sortedLeft = mergeSort(left);
        List<Employee> sortedRight = mergeSort(right);

        // join the two sorted halves into one sorted list
        return merge(sortedLeft, sortedRight);
    }

    // Merges two already-sorted lists into one sorted list
    private List<Employee> merge(List<Employee> left, List<Employee> right) {

        List<Employee> result = new ArrayList<>();

        int i = 0;
        int j = 0;

        // compare the first remaining employee of each list and take the smaller name
        while (i < left.size() && j < right.size()) {
            // "<= 0" keeps equal names in their original order (stable sort)
            if (left.get(i).getName().compareToIgnoreCase(right.get(j).getName()) <= 0) {
                result.add(left.get(i));
                i++;
            } else {
                result.add(right.get(j));
                j++;
            }
        }

        // copy any employees left over in the left list
        while (i < left.size()) {
            result.add(left.get(i));
            i++;
        }

        // copy any employees left over in the right list
        while (j < right.size()) {
            result.add(right.get(j));
            j++;
        }

        return result;
    }
}
