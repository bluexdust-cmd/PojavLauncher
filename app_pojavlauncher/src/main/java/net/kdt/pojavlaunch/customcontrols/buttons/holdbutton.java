Button holdButton = new Button(mcActivity);
holdButton.setText("Hold Button");

holdButton.setOnTouchListener(new View.OnTouchListener() {
    private boolean isHeld = false;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (isHeld) {
                // Replace this with your held action
                MouseUtils.sendKeyPress(AndroidKeyEvent.KEYCODE_SPACE, true);
                handler.postDelayed(this, 100);
            }
        }
    };

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isHeld = true;
                handler.post(runnable);
                return true;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isHeld = false;
                handler.removeCallbacks(runnable);
                // Replace this with key release action if needed
                MouseUtils.sendKeyPress(AndroidKeyEvent.KEYCODE_SPACE, false);
                return true;
        }
        return false;
    }
});

LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT);
params.gravity = Gravity.CENTER;
holdButton.setLayoutParams(params);

// Add the button to your layout (e.g., controlLayout or mainLayout)
controlLayout.addView(holdButton);
