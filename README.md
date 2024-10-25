Special ViewModel and Error Handling Architecture

1. Special ViewModel and Business Error Handling

	•	The Special ViewModel mandates the transmission of a Special Error Type, ensuring that business developers consider API error cases during the development phase. This approach enforces a plan for error handling, steering developers away from solely programming for successful outcomes.

2. Special Error Type for Business Exceptions

	•	The Special Error Type is specifically designed to handle business-related exceptions. While the BaseRepo prioritizes handling common exceptions, non-common exceptions are transformed by the business layer.

	•	If the business layer opts not to transform an exception, the app will crash in Debug mode, logging to Sentry under the Release build.

4. Common Error Handling in BaseViewModel

	•	BaseViewModel manages common error types. Within the AppNavHost, each declared Screen that needs to display common exceptions should use CommonErrorDisplayView to wrap the actual Screen and pass in the specific business ViewModel inheriting from BaseViewModel.

5. Extending BaseRepo to Support Offline Mode

	•	BaseRepo can be extended to BaseSupportOfflineRepo, implementing both network request repositories and an OfflineRepo to support offline mode functionality.

	•	BaseSupportOfflineRepo monitors network status and, upon changes, automatically switches to the OfflineRepo’s API calls.

7. Usage of BaseRepo for Non-Offline Feature Repos

	•	BaseRepo serves as the base class for feature repositories that do not require offline support, providing a flexible architecture for repos with varying requirements.

8. Separation of Common Error Handling and Business Logic

	•	With the actions described above, common errors are handled centrally within the common module. The actual business Screen only needs to interact with its SpecialViewModel and focus on UI and business presentation logic.

	•	No additional code for CommonError handling is necessary within the business Screen.
