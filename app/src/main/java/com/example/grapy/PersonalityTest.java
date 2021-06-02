package com.example.grapy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//import org.tensorflow.lite.examples.textclassification.client.Result;
//import org.tensorflow.lite.examples.textclassification.client.TextClassificationClient;
//
//import java.util.List;

//import org.tensorflow.lite.Interpreter;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.nio.MappedByteBuffer;
//import java.nio.channels.FileChannel;
//import java.util.StringTokenizer;

public class PersonalityTest extends AppCompatActivity implements View.OnClickListener {

    EditText input_comment;
    TextView result;
    Button btnCheck, btnContinue;
//    Interpreter interpreter;
//    private Handler handler;
//    private static final String TAG = "TextClassificationDemo";
//
//    private TextClassificationClient client;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_test);



//        Log.v(TAG, "onCreate");
//
//        client = new TextClassificationClient(getApplicationContext());
//        handler = new Handler();

        input_comment = findViewById(R.id.input_comment);
        result = findViewById(R.id.result);
        btnCheck = findViewById(R.id.btnCheck);
        btnContinue = findViewById(R.id.btnContinue);

        btnCheck.setOnClickListener(this);
        btnContinue.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCheck:

                String res = input_comment.getText().toString().trim();
                if (res.isEmpty()) {
                    input_comment.setError("Your answer is required");
                    input_comment.requestFocus();
                    return;
                } else {
                    btnContinue.setVisibility(View.VISIBLE);
//                    float f = doInference(input_comment.getText().toString());
//                    result.setText("" +f);
                    result.setText("INTJ");
                }



                break;

            case R.id.btnContinue:
                startActivity(new Intent(PersonalityTest.this, GroupTherapy.class));
                break;
        }
    }


//    private MappedByteBuffer loadModelFile() throws IOException {
//        AssetFileDescriptor assetFileDescriptor = this.getAssets().openFd("personality.tflite");
//        FileInputStream fileInputStream = new FileInputStream(assetFileDescriptor.getFileDescriptor());
//        FileChannel fileChannel = fileInputStream.getChannel();
//        long startoffset = assetFileDescriptor.getStartOffset();
//        long length = assetFileDescriptor.getLength();
//        return fileChannel.map(FileChannel.MapMode.READ_ONLY,startoffset,length);
//    }
//
//    public float doInference(String val) {
////        float[] input = new float[1];
////        input[0] = Float.valueOf(val);
//
//        StringTokenizer input = new StringTokenizer(val);
//
//
//        float[][] output = new float[1][1];
//        interpreter.run(input.nextToken(), output);
//        float inferredValue = output[0][0];
//        return inferredValue;
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.v(TAG, "onStart");
//        handler.post(
//                () -> {
//                    client.load();
//                });
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.v(TAG, "onStop");
//        handler.post(
//                () -> {
//                    client.unload();
//                });
//    }
//
//    /** Send input text to TextClassificationClient and get the classify messages. */
//    private void classify(final String text) {
//        handler.post(
//                () -> {
//                    // Run text classification with TF Lite.
//                    List<Result> results = client.classify(text);
//
//                    // Show classification result on screen
//                    showResult(text, results);
//                });
//    }
//
//    /** Show classification result on the screen. */
//    private void showResult(final String inputText, final List<Result> results) {
//        // Run on UI thread as we'll updating our app UI
//        runOnUiThread(
//                () -> {
//                    String textToShow = "Input: " + inputText + "\nOutput:\n";
//                    for (int i = 0; i < results.size(); i++) {
//                        Result result = results.get(i);
//                        textToShow += String.format("    %s: %s\n", result.getTitle(), result.getConfidence());
//                    }
//                    textToShow += "---------\n";
//
//                    // Append the result to the UI.
//                    result.append(textToShow);
//
//                    // Clear the input text.
//                    input_comment.getText().clear();
//
//                    // Scroll to the bottom to show latest entry's classification result.
////                    scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN));
//                });
//    }


}