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

struct ShelterPersistence {
    
    static var shelters: [Shelter] = []
    
//    static private var sheltersDict : [String: Shelter] {
//        if let sheltersDict = UserDefaults.standard.dictionary(forKey: sheltersKey) as? [String: Shelter] {
//            return sheltersDict
//        }
//        return [:]
//    }
//
//    static func getAllShelters() -> [Shelter] {
//        return sheltersDict.map({ key, value in return value})
//    }
//
//    static func getShelterById(_ _id: String) -> Shelter? {
//        return sheltersDict[_id]
//    }
    
//    static func saveShelter(_ shelter:Shelter) {
//        var dict = sheltersDict
//        dict[shelter._id] = shelter
//        UserDefaults.standard.set(dict, forKey: sheltersKey)
//        UserDefaults.standard.synchronize()
//    }
    
    static func getFavouriteShelters() -> [Shelter] {
        if let favouriteSheltersIds = UserDefaults.standard.array(forKey: favouriteSheltersKey) as? [String] {
            return shelters.filter({ shelter in
                return favouriteSheltersIds.contains(shelter._id)
            })
        }
        return []
    }
    
    static func isFavourite(_ _id: String) -> Bool {
        if let favouriteShelterIds = UserDefaults.standard.array(forKey: favouriteSheltersKey) as? [String] {
            return favouriteShelterIds.contains(_id)
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
    
}
