txtFields = fxer.getAll( 'type:TextField' )

clickOn txtFields[0]
enterText 'add meeting with boss'
waitForFxEvents()
enterText 'add test1'
waitForFxEvents()
enterText 'add test2'
waitForFxEvents()
enterText 'add test3'
waitForFxEvents()


assertThat fxer['textField'], hasText(
        '' )

println 'Test PASSED!'
