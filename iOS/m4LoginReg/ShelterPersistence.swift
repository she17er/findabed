//
//  ShelterPersistence.swift
//  m4LoginReg
//
//  Created by Krrish Dholakia on 3/29/18.
//  Copyright © 2018 Krrish Dholakia. All rights reserved.
//

import UIKit

//let sheltersKey = "shelters"
let favouriteSheltersKey = "favouriteShelters"
let bookedShelterKey = "bookedShelter"
let bedBookedKey = "bedBooked"
struct ShelterPersistence {
    
    static var shelters: [Shelter] = []
    
    static func getFavouriteShelters() -> [Shelter] {
        if let favouriteSheltersIds = UserDefaults.standard.array(forKey: favouriteSheltersKey) as? [String] {
            return shelters.filter({ shelter in
                return favouriteSheltersIds.contains(shelter._id)
            })
        }
        return []
    }
    
    static func getBookedShelter() -> [Shelter] {
        if let bookedShelterId = UserDefaults.standard.string(forKey: bookedShelterKey) {
            return shelters.filter({ shelter in
                return bookedShelterId == shelter._id
            })
        }
        return []
    }
    
    static func getBookedBeds() -> Int {
        if let bookedBeds = UserDefaults.standard.integer(forKey: bedBookedKey) as Int? {
            return bookedBeds
        }
        return 0
    }
    
    static func isFavourite(_ _id: String) -> Bool {
        if let favouriteShelterIds = UserDefaults.standard.array(forKey: favouriteSheltersKey) as? [String] {
            return favouriteShelterIds.contains(_id)
        }
        return false
    }
    
    static func isBooked() -> Bool {
        if let bookedShelter = UserDefaults.standard.string(forKey: bookedShelterKey) {
            print(bookedShelter)
            return !bookedShelter.isEmpty
        }
        return false
    }
    
    static func toggleFavouriteShelterById(_ _id: String) {
        var shelterIds:[String] = []
        if let favouriteShelterIds = UserDefaults.standard.array(forKey: favouriteSheltersKey) as? [String] {
             shelterIds = favouriteShelterIds
        }
        if (shelterIds.contains(_id)) {
            shelterIds.remove(at: shelterIds.index(of: _id)!)
        } else {
            shelterIds.append(_id)
        }
        
        UserDefaults.standard.set(shelterIds, forKey: favouriteSheltersKey)
        UserDefaults.standard.synchronize()
    }
    
    static func toggleBookedShelter(_ _id: String) {
        var bookedShelter: String = ""
        if let bookedShelterSaved = UserDefaults.standard.string(forKey: bookedShelterKey) as? String {
            bookedShelter = bookedShelterSaved
        }
        
        if (bookedShelter == _id) {
            bookedShelter = ""
        } else {
            bookedShelter = _id
        }
        
        UserDefaults.standard.set(bookedShelter, forKey: bookedShelterKey)
        UserDefaults.standard.synchronize()
    }
    
    static func toggleBookedBeds(_ beds: Int) {
        var bedBooked: Int = 0
        if let bedBookedSaved = UserDefaults.standard.integer(forKey: bedBookedKey) as? Int {
            bedBooked = bedBookedSaved
        }
        
        if (bedBooked == beds) {
            bedBooked = 0
        } else {
            bedBooked = beds
        }
        
        UserDefaults.standard.set(bedBooked, forKey: bedBookedKey)
//        UserDefaults.standard.synchronize()
    }
    
}
