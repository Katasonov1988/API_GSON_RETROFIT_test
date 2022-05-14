package com.example.testemployees.screens.employees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.testemployees.R;
import com.example.testemployees.adaptors.EmployeeAdaptor;
import com.example.testemployees.api.ApiFactory;
import com.example.testemployees.api.ApiService;
import com.example.testemployees.pojo.Employee;
import com.example.testemployees.pojo.EmployeeResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeeListActivity extends AppCompatActivity implements EmployeesListView {
    private RecyclerView recyclerViewEmployees;
    private EmployeeAdaptor adaptor;
    private EmployeeListPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new EmployeeListPresenter(this);

        recyclerViewEmployees = findViewById(R.id.recyclerViewEmployees);
        adaptor = new EmployeeAdaptor();
        adaptor.setEmployees(new ArrayList<Employee>());
        recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmployees.setAdapter(adaptor);
        presenter.loadData();
    }

    public void showData (List<Employee> employees) {
        adaptor.setEmployees(employees);
    }
    public void showError () {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        presenter.disposeDisposable();
        super.onDestroy();
    }
}