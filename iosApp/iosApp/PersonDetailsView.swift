import SwiftUI
import shared

struct PersonDetailsView: View {
    @ObservedObject private(set) var personDetailsViewModel: PersonDetailsViewModel
    
    var body: some View {
        if let person = personDetailsViewModel.person {
            Text("\(person.name) was born on \(person.birthYear)")
        }
    }
}

class PersonDetailsViewModel: ObservableObject {
    @Published var person: Person? = nil

    init(personId: Int64, starWarsRepository: StarWarsRepository) {
        starWarsRepository.getPerson(id: personId) { person, error in
            self.person = person
        }
    }
}
