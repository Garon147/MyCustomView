package orion.garon.mycustomview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Path;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button colorButton;
    private CustomView customView;
    private Random random;

    private final int[] colorArray = {Color.BLACK, Color.BLUE, Color.CYAN, Color.GRAY,
            Color.DKGRAY, Color.GREEN, Color.RED, Color.YELLOW, Color.MAGENTA};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random = new Random();

        colorButton = findViewById(R.id.color_button);
        colorButton.setOnClickListener(this);
        customView = findViewById(R.id.custom_view);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.color_button)
        {
            if(customView.isFirstLaunch()) {

//                int r = random.nextInt(255);
//                int g = random.nextInt(255);
//                int b = random.nextInt(255);
                int startColor = colorArray[random.nextInt(colorArray.length)];
                customView.setStartColor(startColor);
            }
        }
    }
}
