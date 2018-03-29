//
//  coOrdinatesStruct.swift
//  m4LoginReg
//
//  Created by Krrish Dholakia on 3/28/18.
//  Copyright © 2018 Krrish Dholakia. All rights reserved.
//

import Foundation

struct CoOrdinates: Codable {

    let name: String
    let phoneNumber: Int
    let acceptedTypes: [String]
    let currCapacity: Int
}
