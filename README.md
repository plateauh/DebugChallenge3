# DebugChallenge3
Disclaimer: This is not my code, I'm just debugging it.

You can view the branch commits to see which bugs were discovered
Reported Bugs:
    the API always thinks the user has entered the word 'house' -> Fixed
    the definition value is wrong -> Fixed
    the Toast message never appears -> There is 2 toasts with issues, one wasn't shown, the other was executed on the wrong scope
    the app crashes if there is no internet connection -> Didn't happen, but an internet connection check was added to inform the user that the issue is with the connection specifically
    the app crashes when an invalid word is entered -> Didn't happen at all 🧐 maybe that was related the toast with the wrong scope?
