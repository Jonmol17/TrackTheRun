
import Link from 'next/link'
import React from 'react'

function Header() {

  
  return (
    <div className=''>
      <h1 className=''>
        Track The Run
      </h1>

      <ul className=''>
        <li className=''>
          <Link href="/login">Logga in</Link>
        </li>
      </ul>
    </div>
  )
}

export default Header
