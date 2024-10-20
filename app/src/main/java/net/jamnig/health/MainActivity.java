package net.jamnig.health;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import net.jamnig.health.database.DatabaseHandler;
import net.jamnig.health.databinding.ActivityMainBinding;
import net.jamnig.health.fragments.CompassFragment;
import net.jamnig.health.fragments.GpsFragment;
import net.jamnig.health.fragments.HealthFragment;
import net.jamnig.health.fragments.HistoryFragment;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String SELECTED_FRAGMENT_TAG = "selected_fragment_tag";
    private static final int DEFAULT_SELECTED_ITEM_ID = R.id.gps;
    ActivityMainBinding binding;
    private String selectedFragmentTag; // To store the currently selected fragment tag
    private String[] motivationMessages;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.bottomNavigationView.setSelectedItemId(DEFAULT_SELECTED_ITEM_ID); // start position
//        binding.floatingPoint.setOnClickListener(view -> handleFloatingPointClick());     // TODO

        // Disables placeholder click in the middle
        MenuItem disabledMenuItem = binding.bottomNavigationView.getMenu().findItem(R.id.placeholder);
        disabledMenuItem.setEnabled(false);

        // Restores old fragment when e.g. layout switched to darkmode
        if (savedInstanceState != null) {
            selectedFragmentTag = savedInstanceState.getString(SELECTED_FRAGMENT_TAG);
            if (selectedFragmentTag != null) {
                restoreFragment(selectedFragmentTag);
            }
        } else {
            startGeneralFragment();
        }

        motivationMessages = getResources().getStringArray(R.array.motivation_messages);
        random = new Random();
        setContentView(binding.getRoot());

        switchFragments();
        checkAndRequestNotificationPermission();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString(SELECTED_FRAGMENT_TAG, selectedFragmentTag);
    }

    private void handleFloatingPointClick() {
        int randomIndex = random.nextInt(motivationMessages.length);
        Toast.makeText(this, motivationMessages[randomIndex], Toast.LENGTH_SHORT).show();
    }

    private void switchFragments() {
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.health) {
                replaceFragment(new HealthFragment());
            } else if (id == R.id.gps) {
                replaceFragment(new GpsFragment());
            } else if (id == R.id.compass) {
                replaceFragment(new CompassFragment());
            } else if (id == R.id.history) {
                replaceFragment(new HistoryFragment());
            }
            return true;
        });
    }

    private void restoreFragment(String fragmentTag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(fragmentTag);
        if (fragment != null) {
            replaceFragment(fragment);
        } else {
            // If the fragment is not found (e.g., due to changes in app structure),
            // starts the default fragment or any other appropriate action
            startGeneralFragment();
        }
    }

    private void startGeneralFragment() {
        if (getIntent().getAction() != null && getIntent().getAction().equals("OPEN_FRAGMENT")) {
            String fragmentName = getIntent().getStringExtra("gpsFragmentOpen");
            if (fragmentName != null && fragmentName.equals("gpsTracking")) {
                replaceFragment(new GpsFragment());
            }
        } else {
            replaceFragment(new GpsFragment()); // Main page - Can be changed
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    // TODO rework alertDialog layout and move stuff to permission handler
    // TODO allgemein nach der reihe die permissions mit alertDialog durchgehen weil derzeit funzt das vergeben nicht richtig
    private void checkAndRequestNotificationPermission() {
        // Only request notification permission on API level 33 (Android 13) or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    == PackageManager.PERMISSION_GRANTED) {
                // Permission is already granted, you can proceed with showing notifications
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.POST_NOTIFICATIONS)) {
                // Show rationale and then request permission
                showNotificationPermissionRationale();
            } else {
                // Directly request the permission
                requestNotificationPermission();
            }
        }
    }

    // Method to request the notification permission
    private void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
        }
    }

    // Show a rationale dialog to explain why you need the permission
    private void showNotificationPermissionRationale() {
        new AlertDialog.Builder(this)
                .setTitle("Notification Permission Required")
                .setMessage("This app needs permission to send notifications.")
                .setPositiveButton("Allow", (dialog, which) -> requestNotificationPermission())
                .setNegativeButton("Deny", null)
                .show();
    }

    // Registering the ActivityResultLauncher to handle the permission request result
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission granted, proceed with notifications
                } else {
                    // Permission denied, handle the denial
                }
            });

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DatabaseHandler.closeDatabase();
    }
}