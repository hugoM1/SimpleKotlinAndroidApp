# More documentation about how to customize your build
# can be found here:
# https://docs.fastlane.tools
fastlane_version "2.12.0"

# This value helps us track success metrics for Fastfiles
# we automatically generate. Feel free to remove this line
# once you get things running smoothly!
generated_fastfile_id "95238dd9-1900-42f1-84cc-ae3bb7e1c16b"

default_platform :android

# Fastfile actions accept additional configuration, but
# don't worry, fastlane will prompt you for required
# info which you can add here later
lane :beta do
  # build the release variant
  gradle(task: "assembleRelease")

  # upload to Google Play
  supply(track: "beta")

  # slack(
  #   slack_url: "https://hooks.slack.com/services/IDS"
  # )
end
