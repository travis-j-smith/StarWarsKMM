import SwiftUI
import shared

@main
struct iOSApp: App {
    let starWarsRepository = StarWarsRepository(databaseDriverFactory: DatabaseDriverFactory())
	var body: some Scene {
		WindowGroup {
            PeopleView(peopleViewModel: .init(starWarsRepository: starWarsRepository))
		}
	}
}
