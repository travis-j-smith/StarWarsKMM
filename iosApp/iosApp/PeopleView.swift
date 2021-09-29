import SwiftUI
import shared

struct PeopleView: View {
    @ObservedObject private(set) var peopleViewModel: PeopleViewModel
    
    var body: some View {
        NavigationView {
            List(peopleViewModel.people) { person in
                NavigationLink(destination: PersonDetailsView(personDetailsViewModel: .init(personId: person.id, starWarsRepository: peopleViewModel.starWarsRepository))) {
                    Text(person.name)
                }
            }
            .navigationTitle("People from Star Wars")
            .navigationBarTitleDisplayMode(.inline)
        }
    }
}

class PeopleViewModel: ObservableObject {
    @Published var people = [Person]()
    
    let starWarsRepository: StarWarsRepository
    
    init(starWarsRepository: StarWarsRepository) {
        self.starWarsRepository = starWarsRepository
        
        starWarsRepository.getPeople { people, error in
            self.people = people ?? []
        }
    }
}
