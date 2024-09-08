Feature: Refresh Tokens with Various Combinations

  Scenario Outline: Refresh tokens after successful login
    Given I have successfully logged in and "<refershtoken>"
    When I use the refresh token to request new tokens
    Then I should receive a <status> status code
    And the response should contain "idToken" and "accessToken" that are not empty

    Examples:
      | refershtoken                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               | status |
      | eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiUlNBLU9BRVAifQ.EA3wMQwhMLDG4efJpoG3ydBbpluwcJIYq7CwpAAJTsJ7YoGx8H_cyRVYbkZw1Jhv61zuAJ8Of_CEiV6Y__6CbiZ_lqdNNfNbT6sSiPIygQ3PlxwTQjSmUbbw47WMynWJKI-Y0tubR6PeSJxMjGoK-rOMry7UI-RVTeUXYBcI8Q16OxGj5YmS17r9VFQbDIbhfz9HD1J2uc0FnTtyYqhD3bKht237oM69YjKsZx_0hBVsTK3LE658tVbav9rnBxWk0lGkwr4QYJ54W0dcwlc9kaCjb58Om83CPGpGdUYlKQXbFkv1U-nzTysMdy9X_KA16-ES6gtZS62Yexwow1rY2g.ZNyK-Q-TyN-qSFvz.em7W7kg9-txSDcqtamxP0rKpmOtETpzwLSRJlJIzQwZJWJ1XKrbtZE-HIvPAur2wsN4eCF4qMyHbq2btQ_RYetT_A0Y5_TWKxcNJSn0TjY3nxpRnwrNI7V-bofiyS8iNPC_TXden4Rd1FBHTrVjCwxw4J52KvRcPd-bMVLltyCR_2by40c8nxGr_QRrRM-fBynT-TixVB8A5FTkV-K6XC-EEIavMVcbXsrxMuaJ79h9YjJvIvP2F1AHCSG36uFvWJZZuOtzhb1fr7Co45Qa4TMWwJ9S5ylauzeYYFl6xeAfp0ToLduTQOz52DVRS8gw4d_QK0Nk679Kr81W7FzgsHwGFW_BRPsxQ7ObqFHdQ9TG12r9IuY0FWnzv1_OurLvu3rUwPPfZxnJbGU4mlrX_oMxh4y_VOVXfDxxKQIUAN8QHTZiu4F5tJ2bSPwy9SiTSs6agN6WujtbjzQFh5vw-mLxVyMpKc2yoayqry8zeUe7giVuh2l64tP-3YdARAFXZAYnH_l5HXxQ9o2-wxdr9jctPVq3cACzRAF2Ne9MeFexvI2foVtnPvp65S9Uht6B7dM0I5Mf9SOe8S8YTpYr1R5Fv11ZCJjPzFISCu0EwUPXkf5ZZXu6oIbqIaAsloRWLyp6Vc349KZZY7v8pYjT4F7kVnwmmmjFn2HtGuVicQPYjbI6AhPrjLtJ_Rv9tFO-MfN95OVzrJEdIsOGVMv75yV9zoKNst4S63oQXvZKWfxtowRlBqPw6eni9MT1O-dCQwcn7U28BAFe9r5z0a2IBjnNTvwHxrBIwZ4r1SJUHqTflh13FREPNJylcRV8UvUjEh5GyW4GsdMXE76RKh1-tErGXhWPzzFIj2Q6kLAs0xoUGZaQ8cseubYNN-k2oXAeqlyl1WqfI_fdtmWuXL3AhVwSPlfvUtRJgoTkHK2aCH_LSNqN_A-JKLpLyMmd_s7aHN_WK5TIJZkqz7YOcmdpUs531GCAvdEJ_Y1ba5ROl-ByRCWQ_hATDkYiHJ7X7XuHs2PchFLZmcXWITTmGe6XwIwOT7T66t22b1hVgWKChdrSutnJd6VZDx3wlrZPfVf_mB-4ZzpPpRJ2Ao8HvQIKU2aFpwlmstKI0xy-zBIhJumFufWMTfoREdvMkgFafXtUUKLoPIvO_VkryfCm4BZngtrmRhOmlcpLL-diC9w0t4ykc56S7y9SEgQgoTZMknWDSxXROkXQGNXQtR_CpoYn46kfBwQEJ3JXO91WyL0eJ63A1VFZYfOkhsQa_TcM487QCWtCFjSZpyN9-ue2gMAmQ2izMMkHOCpydCo27jEnyPXMRy5_qbxzh9MpSv0fAlw.K3uxCi04fAq5Ezlk4zJb-w | 200    |


  Scenario Outline: Invalid token refresh tests
    Given I have successfully logged in and retrieved the tokens from the previous scenario
    When I use the refresh token "<refreshToken>" to request new tokens
    Then I should receive a <status> status code
    And the response should contain the error message as "<errorMessage>"

    Examples:
      | refreshToken                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             | status | errorMessage                                                                                                            |
      | eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiUlNBLU9BRVAifQ.EA3wMQwhMLDG4efJpoG3ydBbpluwcJIYq7CwpAAJTsJ7YoGx8H_cyRVYbkZw1Jhv61zuAJ8Of_CEiV6Y__6CbiZ_lqdNNfNbT6sSiPIygQ3PlxwTQjSmUbbw47WMynWJKI-Y0tubR6PeSJxMjGoK-rOMry7UI-RVTeUXYBcI8Q16OxGj5YmS17r9VFQbDIbhfz9HD1J2uc0FnTtyYqhD3bKht237oM69YjKsZx_0hBVsTK3LE658tVbav9rnBxWk0lGkwr4QYJ54W0dcwlc9kaCjb58Om83CPGpGdUYlKQXbFkv1U-nzTysMdy9X_KA16-ES6gtZS62Yexwow1rY2g.ZNyK-Q-TyN-qSFvz.em7W7kg9-txSDcqtamxP0rKpmOtETpzwLSRJlJIzQwZJWJ1XKrbtZE-HIvPAur2wsN4eCF4qMyHbq2btQ_RYetT_A0Y5_TWKxcNJSn0TjY3nxpRnwrNI7V-bofiyS8iNPC_TXden4Rd1FBHTrVjCwxw4J52KvRcPd-bMVLltyCR_2by40c8nxGr_QRrRM-fBynT-TixVB8A5FTkV-K6XC-EEIavMVcbXsrxMuaJ79h9YjJvIvP2F1AHCSG36uFvWJZZuOtzhb1fr7Co45Qa4TMWwJ9S5ylauzeYYFl6xeAfp0ToLduTQOz52DVRS8gw4d_QK0Nk679Kr81W7FzgsHwGFW_BRPsxQ7ObqFHdQ9TG12r9IuY0FWnzv1_OurLvu3rUwPPfZxnJbGU4mlrX_oMxh4y_VOVXfDxxKQIUAN8QHTZiu4F5tJ2bSPwy9SiTSs6agN6WujtbjzQFh5vw-mLxVyMpKc2yoayqry8zeUe7giVuh2l64tP-3YdARAFXZAYnH_l5HXxQ9o2-wxdr9jctPVq3cACzRAF2Ne9MeFexvI2foVtnPvp65S9Uht6B7dM0I5Mf9SOe8S8YTpYr1R5Fv11ZCJjPzFISCu0EwUPXkf5ZZXu6oIbqIaAsloRWLyp6Vc349KZZY7v8pYjT4F7kVnwmmmjFn2HtGuVicQPYjbI6AhPrjLtJ_Rv9tFO-MfN95OVzrJEdIsOGVMv75yV9zoKNst4S63oQXvZKWfxtowRlBqPw6eni9MT1O-dCQwcn7U28BAFe9r5z0a2IBjnNTvwHxrBIwZ4r1SJUHqTflh13FREPNJylcRV8UvUjEh5GyW4GsdMXE76RKh1-tErGXhWPzzFIj2Q6kLAs0xoUGZaQ8cseubYNN-k2oXAeqlyl1WqfI_fdtmWuXL3AhVwSPlfvUtRJgoTkHK2aCH_LSNqN_A-JKLpLyMmd_s7aHN_WK5TIJZkqz7YOcmdpUs531GCAvdEJ_Y1ba5ROl-ByRCWQ_hATDkYiHJ7X7XuHs2PchFLZmcXWITTmGe6XwIwOT7T66t22b1hVgWKChdrSutnJd6VZDx3wlrZPfVf_mB-4ZzpPpRJ2Ao8HvQIKU2aFpwlmstKI0xy-zBIhJumFufWMTfoREdvMkgFafXtUUKLoPIvO_VkryfCm4BZngtrmRhOmlcpLL-diC9w0t4ykc56S7y9SEgQgoTZMknWDSxXROkXQGNXQtR_CpoYn46kfBwQEJ3JXO91WyL0eJ63A1VFZYfOkhsQa_TcM487QCWtCFjSZpyN9-ue2gMAmQ2izMMkHOCpydCo27jEnyPXMRy5_qbxzh9MpSv0fAlw.K3uxCi04fAq5Ezlk4 | 400    | Failed to refresh tokens: Invalid Refresh Token (Service: CognitoIdentityProvider, Status Code: 400                     |
      |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          | 400    | Failed to refresh tokens: Missing required parameter REFRESH_TOKEN (Service: CognitoIdentityProvider, Status Code: 400, |
