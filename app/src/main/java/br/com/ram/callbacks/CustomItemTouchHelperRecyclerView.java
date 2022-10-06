package br.com.ram.callbacks;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import br.com.ram.adapters.StudentsRecyclerViewAdapter;

public class CustomItemTouchHelperRecyclerView extends ItemTouchHelper.Callback {
    private StudentsRecyclerViewAdapter studentsRecyclerViewAdapter;

    //Constructor
    public CustomItemTouchHelperRecyclerView(StudentsRecyclerViewAdapter studentsRecyclerViewAdapter) {
        this.studentsRecyclerViewAdapter = studentsRecyclerViewAdapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        //Set the direction of swipe
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        //Set the direction of drag and drop
        int dragAndDropFlags = ItemTouchHelper.DOWN | ItemTouchHelper.UP |ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ;
        //Return the flags loaded
        return makeMovementFlags(dragAndDropFlags, swipeFlags);

    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        //Collect the position of dragged view
        int draggedViewPosition = viewHolder.getAdapterPosition();
        //Collect the position of target view
        int targetViewPosition = target.getAdapterPosition();
        //Exchange the position of students in main list
        this.studentsRecyclerViewAdapter.swapPositionOfViews(draggedViewPosition,targetViewPosition);
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        //Remove the student when the viewholder be swiped in both direction
        this.studentsRecyclerViewAdapter.removeStudentFromList(viewHolder.getAdapterPosition());

    }
}
