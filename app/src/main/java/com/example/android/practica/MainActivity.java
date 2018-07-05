package com.example.android.practica;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.util.Collections.swap;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Integer> vector = new ArrayList<>();
    private TextView textAfisare;
    private TextView afisareVector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText arrayEdit = findViewById(R.id.vectorul_edit_view);
        ImageView ok_image_view = findViewById(R.id.ok_image_view);
        textAfisare = findViewById(R.id.heap_sort_text_view);
        afisareVector = findViewById(R.id.heapify_text_view);

        ok_image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String array = arrayEdit.getText().toString();
                String[] arrayString = array.split(" ");
                try {

                    for (int i = 0; i < arrayString.length; i++) {
                        vector.add(Integer.parseInt(arrayString[i]));
                    }
                    if (vector.size() < 2) {
                        displayToast("Lungimea vectorului trebuie sa fie de minim 2 ! ");
                    }
                    setVisibilitate();
                    heapSort(vector);

                    //showVector(vector);
                    for (int i = 0; i < vector.size(); i++) {
                        System.out.println("Vector: " + vector.get(i));
                    }
                    vector.clear();
                } catch (NumberFormatException nfe) {
                    displayToast("Numar invalid ! ");
                }
            }
        });
    }

    private void setVisibilitate() {
        afisareVector.setVisibility(View.VISIBLE);
        textAfisare.setVisibility(View.VISIBLE);
    }
    private void showVector(ArrayList<Integer> v){
        StringBuilder afisare = new StringBuilder();
        for(int i=0;i<v.size();i++){
            afisare .append(v.get(i));
            afisare.append(" ");
        }
        afisareVector.setText(afisare);
    }
    private void displayToast(CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void heapSort(ArrayList<Integer> v) {
        int l = vector.size();
        heapify(v, l);
        for (int i = l - 1; i > 0; i--) {
            swap(v, 0, i);
            maxHeapify(v, 1, i);
        }
    }

    private void heapify(ArrayList<Integer> v, int heapSize) {

        if (heapSize > v.size()) {
            heapSize = v.size();
        }

        for (int i = heapSize / 2; i > 0; i--) {
            maxHeapify(v, i, heapSize);
        }
    }

    private void maxHeapify(ArrayList<Integer> v, int index, int heapSize) {
        showVector(v);
        int l = index * 2;
        int r = l + 1;
        int largest;

        if (l <= heapSize && v.get(l - 1) > v.get(index - 1)) {
            largest = l;
        } else {
            largest = index;
        }

        if (r <= heapSize && v.get(r - 1) > v.get(largest - 1)) {
            largest = r;
        }

        if (largest != index) {
            int temp = v.get(index - 1);
            v.set(index - 1, v.get(largest - 1));
            v.set(largest - 1, temp);
            maxHeapify(v, largest, heapSize);
        }
    }
}
