package app.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import app.model.Employee;

public class Sorter {

    // Recursive Merge Sort: sorts employees in the order given by the rule (e.g. by name or by rank)
    public List<Employee> mergeSort(List<Employee> employees, Comparator<Employee> rule) {

        // base case: a list with 0 or 1 employees is already sorted
        if (employees.size() <= 1) {
            return employees;
        }

        // split the list into two new halves
        int mid = employees.size() / 2;
        List<Employee> left = new ArrayList<>(employees.subList(0, mid));
        List<Employee> right = new ArrayList<>(employees.subList(mid, employees.size()));

        // recursive step: sort each half
        List<Employee> sortedLeft = mergeSort(left, rule);
        List<Employee> sortedRight = mergeSort(right, rule);

        // join the two sorted halves into one sorted list
        return merge(sortedLeft, sortedRight, rule);
    }

    // Merges two already-sorted lists into one sorted list
    private List<Employee> merge(List<Employee> left, List<Employee> right, Comparator<Employee> rule) {

        List<Employee> result = new ArrayList<>();

        int i = 0;
        int j = 0;

        // compare the first remaining employee of each list and take the one that comes first by the rule
        while (i < left.size() && j < right.size()) {
            // "<= 0" keeps equal employees in their original order (stable sort)
            if (rule.compare(left.get(i), right.get(j)) <= 0) {
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
